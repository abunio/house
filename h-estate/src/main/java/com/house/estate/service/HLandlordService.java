package com.house.estate.service;

import com.house.common.entity.HLandlord;
import com.house.common.model.request.QueryPageBean;
import com.house.common.model.response.PageResult;
import com.house.common.utils.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.house.estate.mapper.HLandlordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description
 * @Author huangW
 * @Date 2020/3/17
 * @Version V1.0
 */
@Service
public class HLandlordService {

    @Autowired
    private HLandlordMapper hLandlordMapper;


    /**
     * 分页
     *
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Map map = new HashMap();
        map.put("value",queryPageBean.getQueryString());
        map.put("status",queryPageBean.getStatus());
        Page<HLandlord> hLandlordList = hLandlordMapper.findHLandlordList(map);
        return new PageResult(hLandlordList.getTotal(), hLandlordList.getResult());
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
