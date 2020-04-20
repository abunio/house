package com.house.sys.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.house.common.entity.estate.HLandlord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface HLandlordMapper {

    int deleteByPrimaryKey(String id);

    int insert(HLandlord record);

    int insertSelective(HLandlord record);

    HLandlord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HLandlord record);

    int updateByPrimaryKey(HLandlord record);

    Page<HLandlord> findHLandlordList(Page<HLandlord> page, @Param("map") Map map);
}