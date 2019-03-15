package com.tensquare.recruit.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.recruit.mapper.EnterpriseMapper;
import com.tensquare.recruit.mapper.RecruitMapper;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.pojo.EnterpriseExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import com.tensquare.recruit.pojo.Recruit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnterpriseService {

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Autowired
    IdWorker idWorker;

    public List<Enterprise> findAll() {
        EnterpriseExample example = new EnterpriseExample();
        return enterpriseMapper.selectByExample(example);
    }

    public void save(Enterprise enterprise) {
        enterprise.setId(idWorker.nextId()+"");
        enterpriseMapper.insert(enterprise);
    }

    public void update(Enterprise enterprise) {
        enterpriseMapper.updateByPrimaryKeySelective(enterprise);
    }

    public void delete(String enterpriseId) {
        enterpriseMapper.deleteByPrimaryKey(enterpriseId);
    }

    public Enterprise findById(String enterpriseId) {
        return enterpriseMapper.selectByPrimaryKey(enterpriseId);
    }

    public List<Enterprise> search(Enterprise enterprise) {
        EnterpriseExample example = new EnterpriseExample();
        EnterpriseExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(enterprise.getId())) {
               criteria.andIdEqualTo(enterprise.getId());
            }
          	if (StringUtils.isNotBlank(enterprise.getName())) {
               criteria.andIdEqualTo(enterprise.getName());
            }
          	if (StringUtils.isNotBlank(enterprise.getSummary())) {
               criteria.andIdEqualTo(enterprise.getSummary());
            }
          	if (StringUtils.isNotBlank(enterprise.getAddress())) {
               criteria.andIdEqualTo(enterprise.getAddress());
            }
          	if (StringUtils.isNotBlank(enterprise.getLabels())) {
               criteria.andIdEqualTo(enterprise.getLabels());
            }
          	if (StringUtils.isNotBlank(enterprise.getCoordinate())) {
               criteria.andIdEqualTo(enterprise.getCoordinate());
            }
          	if (StringUtils.isNotBlank(enterprise.getIshot())) {
               criteria.andIdEqualTo(enterprise.getIshot());
            }
          	if (StringUtils.isNotBlank(enterprise.getLogo())) {
               criteria.andIdEqualTo(enterprise.getLogo());
            }
          	if (StringUtils.isNotBlank(enterprise.getUrl())) {
               criteria.andIdEqualTo(enterprise.getUrl());
            }

        return enterpriseMapper.selectByExample(example);
    }

    public PageResult<Enterprise> searchByPage(Enterprise enterprise, int page, int size) {
        PageHelper.startPage(page-1,size);
        EnterpriseExample example = new EnterpriseExample();
        EnterpriseExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(enterprise.getId())) {
               criteria.andIdEqualTo(enterprise.getId());
            }
          	if (StringUtils.isNotBlank(enterprise.getName())) {
               criteria.andIdEqualTo(enterprise.getName());
            }
          	if (StringUtils.isNotBlank(enterprise.getSummary())) {
               criteria.andIdEqualTo(enterprise.getSummary());
            }
          	if (StringUtils.isNotBlank(enterprise.getAddress())) {
               criteria.andIdEqualTo(enterprise.getAddress());
            }
          	if (StringUtils.isNotBlank(enterprise.getLabels())) {
               criteria.andIdEqualTo(enterprise.getLabels());
            }
          	if (StringUtils.isNotBlank(enterprise.getCoordinate())) {
               criteria.andIdEqualTo(enterprise.getCoordinate());
            }
          	if (StringUtils.isNotBlank(enterprise.getIshot())) {
               criteria.andIdEqualTo(enterprise.getIshot());
            }
          	if (StringUtils.isNotBlank(enterprise.getLogo())) {
               criteria.andIdEqualTo(enterprise.getLogo());
            }
          	if (StringUtils.isNotBlank(enterprise.getUrl())) {
               criteria.andIdEqualTo(enterprise.getUrl());
            }

        List<Enterprise> enterpriseList = enterpriseMapper.selectByExample(example);
        PageInfo<Enterprise> pageInfo = new PageInfo<>(enterpriseList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    public List<Enterprise> findHotList(){
        EnterpriseExample example = new EnterpriseExample();
        EnterpriseExample.Criteria criteria = example.createCriteria();
        criteria.andIshotEqualTo("1");
        return enterpriseMapper.selectByExample(example);
    }




}
