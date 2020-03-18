package com.sys.mapper;

import com.common.entity.HLandlord;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HLandlordMapper {

    int deleteByPrimaryKey(String id);

    int insert(HLandlord record);

    int insertSelective(HLandlord record);

    HLandlord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HLandlord record);

    int updateByPrimaryKey(HLandlord record);

    Page<HLandlord> findHLandlordList();
}