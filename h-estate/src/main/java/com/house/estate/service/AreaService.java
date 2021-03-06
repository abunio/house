/**
 *
 */
package com.house.estate.service;


import com.google.common.collect.Lists;


import com.house.common.entity.estate.AreaCascader;
import com.house.common.utils.redis.RedisUtil;
import com.house.estate.mapper.AreaMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Exception.class)
@CacheConfig(cacheNames = "areaCache")
@Slf4j
public class AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询省市区级联
     *
     * @return
     */
    //@Cacheable(value = "areas")
    public List<AreaCascader> queryAreaCascader() {

        List<AreaCascader> areas = areaMapper.executeForObjectList();
        if (CollectionUtils.isEmpty(areas)) {
            return areas;
        }
        List<AreaCascader> areaTreeRespDtos = getAreaTreeRespDtos(areas);
        return areaTreeRespDtos;
    }

    private List<AreaCascader> getAreaTreeRespDtos(List<AreaCascader> areas) {
        areas.forEach(p -> {
            if (!p.getParentId().equals("00000-00000-00000-00000")) {
                areas.forEach(q -> {
                    if (q.getValue().equals(p.getParentId())) {
                        if (CollectionUtils.isEmpty(q.getChildren())) {
                            List<AreaCascader> es = Lists.newArrayList();
                            es.add(p);
                            q.setChildren(es);
                        } else {
                            q.getChildren().add(p);
                        }
                    }
                });
            }
        });

        return areas.stream().filter(e -> e.getParentId().equals("00000-00000-00000-00000"))
                .collect(Collectors.toList());
    }


}
