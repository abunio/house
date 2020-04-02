package com.house.estate.mapper;

import com.house.common.entity.sys.HUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface HUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(HUser record);

    int insertSelective(HUser record);

    HUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HUser record);

    int updateByPrimaryKey(HUser record);
}
