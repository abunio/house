package com.sys.mapper;

import com.common.entity.HUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: TODO
 * @author: huangW
 * @createDate: 2020/3/16 21:10
 * @version: 1.0
 */
@Mapper
public interface HUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(HUser record);

    int insertSelective(HUser record);

    HUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HUser record);

    int updateByPrimaryKey(HUser record);
}
