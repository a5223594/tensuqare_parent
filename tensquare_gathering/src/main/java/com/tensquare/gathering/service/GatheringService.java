package com.tensquare.gathering.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.gathering.mapper.GatheringMapper;
import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.pojo.GatheringExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import com.tensquare.gathering.pojo.GatheringWithBLOBs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GatheringService {

    @Autowired
    GatheringMapper gatheringMapper;

    @Autowired
    IdWorker idWorker;

    public List<Gathering> findAll() {
        GatheringExample example = new GatheringExample();
        return gatheringMapper.selectByExample(example);
    }


    public void save(GatheringWithBLOBs gathering) {
        gathering.setId(idWorker.nextId()+"");
        gatheringMapper.insert(gathering);
    }

    @CacheEvict(value = "gathering",key = "#gathering.id")
    public void update(GatheringWithBLOBs gathering) {
        gatheringMapper.updateByPrimaryKeySelective(gathering);
    }

    @CacheEvict(value = "gathering",key = "#gatheringId")
    public void delete(String gatheringId) {
        gatheringMapper.deleteByPrimaryKey(gatheringId);
    }

    @Cacheable(value = "gathering",key = "#gatheringId")
    public Gathering findById(String gatheringId) {
        return gatheringMapper.selectByPrimaryKey(gatheringId);
    }

    public List<Gathering> search(Gathering gathering) {
        GatheringExample example = new GatheringExample();
        GatheringExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(gathering.getId())) {
               criteria.andIdEqualTo(gathering.getId());
            }
          	if (StringUtils.isNotBlank(gathering.getName())) {
               criteria.andIdEqualTo(gathering.getName());
            }

          	if (StringUtils.isNotBlank(gathering.getSponsor())) {
               criteria.andIdEqualTo(gathering.getSponsor());
            }
          	if (StringUtils.isNotBlank(gathering.getImage())) {
               criteria.andIdEqualTo(gathering.getImage());
            }
          	if (StringUtils.isNotBlank(gathering.getAddress())) {
               criteria.andIdEqualTo(gathering.getAddress());
            }
          	if (StringUtils.isNotBlank(gathering.getState())) {
               criteria.andIdEqualTo(gathering.getState());
            }
          	if (StringUtils.isNotBlank(gathering.getCity())) {
               criteria.andIdEqualTo(gathering.getCity());
            }

        return gatheringMapper.selectByExample(example);
    }

    public PageResult<Gathering> searchByPage(Gathering gathering, int page, int size) {
        PageHelper.startPage(page-1,size);
        GatheringExample example = new GatheringExample();
        GatheringExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(gathering.getId())) {
               criteria.andIdEqualTo(gathering.getId());
            }
          	if (StringUtils.isNotBlank(gathering.getName())) {
               criteria.andIdEqualTo(gathering.getName());
            }

          	if (StringUtils.isNotBlank(gathering.getSponsor())) {
               criteria.andIdEqualTo(gathering.getSponsor());
            }
          	if (StringUtils.isNotBlank(gathering.getImage())) {
               criteria.andIdEqualTo(gathering.getImage());
            }
          	if (StringUtils.isNotBlank(gathering.getAddress())) {
               criteria.andIdEqualTo(gathering.getAddress());
            }
          	if (StringUtils.isNotBlank(gathering.getState())) {
               criteria.andIdEqualTo(gathering.getState());
            }
          	if (StringUtils.isNotBlank(gathering.getCity())) {
               criteria.andIdEqualTo(gathering.getCity());
            }

        List<Gathering> gatheringList = gatheringMapper.selectByExample(example);
        PageInfo<Gathering> pageInfo = new PageInfo<>(gatheringList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
