package com.sys.service;

import com.common.entity.HLandlord;
import com.common.model.response.PageResult;
import com.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sys.dao.HLandlordRepository;
import com.sys.mapper.HLandlordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    @Autowired
    private HLandlordRepository hLandlordRepository;

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    public PageResult findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<HLandlord> hLandlordList = hLandlordMapper.findHLandlordList();
        return new PageResult(hLandlordList.getTotal(),hLandlordList.getResult());
    }

    /**
     * 保存
     * @param hLandlord
     * @return
     */
    public void add(HLandlord hLandlord) {
        hLandlord.setId(StringUtils.uuid());
        hLandlord.setDel(0);
        hLandlordRepository.save(hLandlord);
    }
}
