package com.house.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.house.common.entity.sys.HUser;



public interface HUserMapper extends BaseMapper<HUser> {

    int deleteByPrimaryKey(String id);

    int insert(HUser record);

    int insertSelective(HUser record);

    HUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HUser record);

    int updateByPrimaryKey(HUser record);

    Integer selectCountByUserName(String username);

    HUser selectByUserName(String username);
}
