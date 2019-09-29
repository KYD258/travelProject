package com.qf.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Component
public class EmailUtils {

    @Resource
    private JavaMailSender javaMailSender;
    @Value("824238833@qq.com")
    private String from;


    public String sendEmail(String sendTo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText("注册验证码");
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(getCode());
        simpleMailMessage.setTo(sendTo);
        try {
            javaMailSender.send(simpleMailMessage);
            return getCode();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static String getCode(){
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for(int i=0; i<6; i++){
            int r = random.nextInt(9);
            code.append(r);
        }
       return code.toString();
    }
}
