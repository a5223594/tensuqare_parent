package com.tensquare.recruit.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.recruit.mapper.RecruitMapper;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.pojo.RecruitExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecruitService {

    @Autowired
    RecruitMapper recruitMapper;

    @Autowired
    IdWorker idWorker;

    public List<Recruit> findAll() {
        RecruitExample example = new RecruitExample();
        return recruitMapper.selectByExample(example);
    }

    public void save(Recruit recruit) {
        recruit.setId(idWorker.nextId()+"");
        recruitMapper.insert(recruit);
    }

    public void update(Recruit recruit) {
        recruitMapper.updateByPrimaryKeySelective(recruit);
    }

    public void delete(String recruitId) {
        recruitMapper.deleteByPrimaryKey(recruitId);
    }

    public Recruit findById(String recruitId) {
        return recruitMapper.selectByPrimaryKey(recruitId);
    }

    public List<Recruit> search(Recruit recruit) {
        RecruitExample example = new RecruitExample();
        RecruitExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(recruit.getId())) {
               criteria.andIdEqualTo(recruit.getId());
            }
          	if (StringUtils.isNotBlank(recruit.getJobname())) {
               criteria.andIdEqualTo(recruit.getJobname());
            }
          	if (StringUtils.isNotBlank(recruit.getSalary())) {
               criteria.andIdEqualTo(recruit.getSalary());
            }
          	if (StringUtils.isNotBlank(recruit.getCondition())) {
               criteria.andIdEqualTo(recruit.getCondition());
            }
          	if (StringUtils.isNotBlank(recruit.getEducation())) {
               criteria.andIdEqualTo(recruit.getEducation());
            }
          	if (StringUtils.isNotBlank(recruit.getType())) {
               criteria.andIdEqualTo(recruit.getType());
            }
          	if (StringUtils.isNotBlank(recruit.getAddress())) {
               criteria.andIdEqualTo(recruit.getAddress());
            }
          	if (StringUtils.isNotBlank(recruit.getEid())) {
               criteria.andIdEqualTo(recruit.getEid());
            }
          	if (StringUtils.isNotBlank(recruit.getState())) {
               criteria.andIdEqualTo(recruit.getState());
            }
          	if (StringUtils.isNotBlank(recruit.getUrl())) {
               criteria.andIdEqualTo(recruit.getUrl());
            }
          	if (StringUtils.isNotBlank(recruit.getLabel())) {
               criteria.andIdEqualTo(recruit.getLabel());
            }
          	if (StringUtils.isNotBlank(recruit.getContent1())) {
               criteria.andIdEqualTo(recruit.getContent1());
            }
          	if (StringUtils.isNotBlank(recruit.getContent2())) {
               criteria.andIdEqualTo(recruit.getContent2());
            }

        return recruitMapper.selectByExample(example);
    }

    public PageResult<Recruit> searchByPage(Recruit recruit, int page, int size) {
        PageHelper.startPage(page-1,size);
        RecruitExample example = new RecruitExample();
        RecruitExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(recruit.getId())) {
               criteria.andIdEqualTo(recruit.getId());
            }
          	if (StringUtils.isNotBlank(recruit.getJobname())) {
               criteria.andIdEqualTo(recruit.getJobname());
            }
          	if (StringUtils.isNotBlank(recruit.getSalary())) {
               criteria.andIdEqualTo(recruit.getSalary());
            }
          	if (StringUtils.isNotBlank(recruit.getCondition())) {
               criteria.andIdEqualTo(recruit.getCondition());
            }
          	if (StringUtils.isNotBlank(recruit.getEducation())) {
               criteria.andIdEqualTo(recruit.getEducation());
            }
          	if (StringUtils.isNotBlank(recruit.getType())) {
               criteria.andIdEqualTo(recruit.getType());
            }
          	if (StringUtils.isNotBlank(recruit.getAddress())) {
               criteria.andIdEqualTo(recruit.getAddress());
            }
          	if (StringUtils.isNotBlank(recruit.getEid())) {
               criteria.andIdEqualTo(recruit.getEid());
            }
          	if (StringUtils.isNotBlank(recruit.getState())) {
               criteria.andIdEqualTo(recruit.getState());
            }
          	if (StringUtils.isNotBlank(recruit.getUrl())) {
               criteria.andIdEqualTo(recruit.getUrl());
            }
          	if (StringUtils.isNotBlank(recruit.getLabel())) {
               criteria.andIdEqualTo(recruit.getLabel());
            }
          	if (StringUtils.isNotBlank(recruit.getContent1())) {
               criteria.andIdEqualTo(recruit.getContent1());
            }
          	if (StringUtils.isNotBlank(recruit.getContent2())) {
               criteria.andIdEqualTo(recruit.getContent2());
            }

        List<Recruit> recruitList = recruitMapper.selectByExample(example);
        PageInfo<Recruit> pageInfo = new PageInfo<>(recruitList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state){
        return recruitMapper.findTop4ByStateOrderByCreatetimeDesc(state);
    }

    public List<Recruit> findTop12ByStateOrderByCreatetimeDesc(String state){
        return recruitMapper.findTop12ByStateOrderByCreatetimeDesc(state);
    }

}
