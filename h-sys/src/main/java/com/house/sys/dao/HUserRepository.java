package com.house.sys.dao;

import com.house.common.entity.HUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: TODO
 * @author: huangW
 * @createDate: 2020/3/16 20:48
 * @version: 1.0
 */

public interface HUserRepository extends JpaRepository<HUser,String> {

}
