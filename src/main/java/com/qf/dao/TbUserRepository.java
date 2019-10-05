package com.qf.dao;

import com.qf.domain.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbUserRepository extends JpaRepository<TbUser,Integer> {
}
