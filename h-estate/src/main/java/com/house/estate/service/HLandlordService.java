package com.house.estate.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.common.entity.estate.HLandlord;
import com.house.common.model.request.QueryPageBean;
import com.house.common.model.response.PageResult;
import com.house.common.utils.StringUtil;
import com.house.estate.mapper.HLandlordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description
 * @Author huangW
 * @Date 2020/3/17
 * @Version V1.0
 */
@Service
public class HLandlordService  {

    @Autowired
    private HLandlordMapper hLandlordMapper;


    /**
     * 分页
     *
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean) {
        Page<HLandlord> page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Map map = new HashMap();
        map.put("value",queryPageBean.getQueryString());
        map.put("status",queryPageBean.getStatus());
       // String status = queryPageBean.getStatus();
        IPage<HLandlord> pageHLandlord = hLandlordMapper.findHLandlordList(page,map);
        return new PageResult(pageHLandlord.getTotal(), pageHLandlord.getRecords());
//        Page<HLandlord> page = new Page(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
//        QueryWrapper<HLandlord> queryWrapper = new QueryWrapper();
//        if(StringUtils.isNotBlank(queryPageBean.getQueryString())){
//            queryWrapper.or().like("phone",queryPageBean.getQueryString());
//            queryWrapper.or().like("community",queryPageBean.getQueryString());
//            queryWrapper.or().like("landlord",queryPageBean.getQueryString());
//        }
//        if(StringUtils.isNotBlank(queryPageBean.getStatus())){
//            queryWrapper.eq("status",queryPageBean.getStatus());
//        }
//        queryWrapper.orderByDesc("modified_date");
//        IPage<HLandlord> pages = hLandlordMapper.selectPage(page, queryWrapper);
//        if(page!=null){
//            return new PageResult(pages.getTotal(), pages.getRecords());
//        }
//        return null;
    }

    /**
     * 新增和编辑
     *
     * @param hLandlord
     * @return
     */
    public String add(HLandlord hLandlord) {
        if (StringUtils.isBlank(hLandlord.getId())) {
            hLandlord.setId(StringUtil.uuid());
            hLandlord.setDel(0);
            hLandlord.setModifiedDate(new Date());
            hLandlordMapper.insertSelective(hLandlord);
            return "新增成功";
        } else {
            hLandlord.setModifiedDate(new Date());
            hLandlordMapper.updateByPrimaryKeySelective(hLandlord);
            return "编辑成功";
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public HLandlord findById(String id) {
        HLandlord hLandlord = hLandlordMapper.selectByPrimaryKey(id);
        if (hLandlord == null) {
           throw new RuntimeException("HLandlord查询为空");
        }
        return hLandlord;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public String delete(String id) {
        int i = hLandlordMapper.deleteByPrimaryKey(id);
        if (i > 0)
            return "删除成功";
        return "请重试";
    }
}
