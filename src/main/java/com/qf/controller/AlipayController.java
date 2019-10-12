package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qf.config.AlipayConfig;
import com.qf.dao.CartMapper;
import com.qf.dao.CartRepository;
import com.qf.dao.SysOrderMapper;
import com.qf.dao.SysOrderRepository;
import com.qf.domain.Cart;
import com.qf.domain.SysOrder;
import com.qf.domain.SysRoute;
import com.qf.utils.AlipayUtils;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/alipay")
public class AlipayController {
    
    @Autowired
    private AlipayUtils alipayUtils;
    @Resource
    private CartMapper cartMapper;
    @Autowired
    private SysOrderRepository sysOrderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Resource
    private SysOrderMapper sysOrderMapper;

    @RequestMapping("/pay")
    public String pay(@RequestBody SysRoute sysRoute, HttpSession httpSession){
        Integer userId =(Integer) httpSession.getAttribute("userId");
        Integer routeId = sysRoute.getRouteId();
        SysOrder sysOrder=new SysOrder();
        sysOrder.setRouteId(routeId);
        sysOrder.setUserId(userId);
        StringBuffer stringBuffer=new StringBuffer();
        Random random=new Random();
        String orderNum="";
        for (int i = 0; i <18 ; i++) {

            StringBuffer append = stringBuffer.append(random.nextInt(9));
            orderNum = append.toString();
        }
        sysOrder.setOrderNum(orderNum);
        sysOrder.setOrderStatus(0);
        SysOrder save = sysOrderRepository.save(sysOrder);
        Cart c=new Cart();
        c.setUserId(userId);
        c.setRouteId(routeId);
        Cart byUserIdAndRouteId = cartMapper.findByUserIdAndRouteId(c);
        cartRepository.delete(byUserIdAndRouteId);
        String pay="";
        try {
            pay = alipayUtils.pay(sysRoute,orderNum);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(pay);

        return pay;
    }

    @RequestMapping("/payAll")
    public String payAll(HttpSession httpSession){
        SysRoute sysRoute=new SysRoute();
        StringBuffer stringBuffer=new StringBuffer();
        Integer userId =(Integer) httpSession.getAttribute("userId");
        List<SysRoute> sysRoutes = cartMapper.cartSelectAll(userId);
        Float aFloat = cartMapper.countPrice(userId);
        String s1="";
        String orderNum="";
        Random random=new Random();
        for (int i = 0; i <18 ; i++) {
            StringBuffer append = stringBuffer.append(random.nextInt(9));
            orderNum = append.toString();
        }
        for (SysRoute s:sysRoutes) {

            StringBuffer append1 = stringBuffer.append(s.getRouteName());
             s1 = append1.toString();
            SysOrder sysOrder=new SysOrder();
            sysOrder.setRouteId(s.getRouteId());
            sysOrder.setUserId(userId);
            sysOrder.setOrderStatus(0);
            sysOrder.setOrderNum(orderNum);
            SysOrder save = sysOrderRepository.save(sysOrder);
        }
        cartMapper.deleteAll(userId);
        sysRoute.setRouteName(s1);
        sysRoute.setRoutePrice(aFloat);
        System.out.println(orderNum+"==>>");

        String pay="";
        try {
            pay = alipayUtils.pay(sysRoute,orderNum);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(pay);
        return pay;
    }

    @RequestMapping("/del")
    public String del(@RequestBody SysRoute sysRoute,HttpSession httpSession){
        Integer userId =(Integer) httpSession.getAttribute("userId");
        Integer routeId = sysRoute.getRouteId();
        Cart c=new Cart();
        c.setUserId(userId);
        c.setRouteId(routeId);
        Cart byUserIdAndRouteId = cartMapper.findByUserIdAndRouteId(c);
        cartRepository.delete(byUserIdAndRouteId);

        return "删除成功";
    }

    @RequestMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response){
        System.out.println("f==========================================>>");
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<>(30);
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }
            // 商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 付款金额
            String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            // 交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            // APPID
            String appId = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

            System.out.println(params);
                //业务处理
                if (signVerified) {
//                    询支付金额用于支付金额验证
                    //根据返回的订单号查
//                    BigDecimal payPrice = payDao.getPayPrice(outTradeNo);
                    //普通即时到帐状态下交易成功状态
                    String normalTradeStatus = "TRADE_FINISHED";
                    //高级即时到帐状态下易成功状态
                    String advancedTradeStatus = "TRADE_SUCCESS";

                    //支付金额、订单完成标识、appid验证
//                    Boolean priceFlag = new BigDecimal(totalAmount).compareTo(payPrice) == 0;
                    Boolean tradeFlag = normalTradeStatus.equals(tradeStatus) || advancedTradeStatus.equals(tradeStatus);
                    Boolean appidFlag = AlipayConfig.app_id.equals(appId);
                    if (tradeFlag && appidFlag) {
                        sysOrderMapper.updateStatus(outTradeNo);

//                        //将订单状态改为预定中（支付成功）
//                        payDao.updateOrderStateByNo(outTradeNo, BookingConst.STATE_BOOKING.getCode(), CommonConstant.ALIPAY, CommonConstant.PAYSUCCESS);
                        response.getWriter().write("success");
                    } else {
                        //将订单状态改为支付失败
//                        payDao.updateOrderStateByNo(outTradeNo, BookingConst.STATE_ORDER_PAY_FAIL.getCode(), CommonConstant.ALIPAY, CommonConstant.PAYFAIL);
                        response.getWriter().write("failure");
                    }
                } else {
                    response.getWriter().write("failure");
                }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

