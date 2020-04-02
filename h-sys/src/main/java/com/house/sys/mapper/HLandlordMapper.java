package com.house.sys.mapper;

import com.house.common.entity.HLandlord;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface HLandlordMapper {

    int deleteByPrimaryKey(String id);

    int insert(HLandlord record);

    int insertSelective(HLandlord record);

    HLandlord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HLandlord record);

    int updateByPrimaryKey(HLandlord record);

    Page<HLandlord> findHLandlordList(Map map);
}