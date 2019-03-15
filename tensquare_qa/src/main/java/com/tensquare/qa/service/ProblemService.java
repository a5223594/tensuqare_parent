package com.tensquare.qa.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.qa.mapper.ProblemMapper;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.pojo.ProblemExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProblemService {

    @Autowired
    ProblemMapper problemMapper;

    @Autowired
    IdWorker idWorker;

    public List<Problem> findAll() {
        ProblemExample example = new ProblemExample();
        return problemMapper.selectByExample(example);
    }

    public void save(Problem problem) {
        problem.setId(idWorker.nextId() + "");
        problem.setCreatetime(new Date());
        problem.setUpdatetime(new Date());
        problem.setReply(0L);
        problem.setVisits(0L);
        problem.setThumbup(0L);
        problem.setSolve("0");
        problemMapper.insert(problem);
    }

    public void update(Problem problem) {
        problemMapper.updateByPrimaryKeySelective(problem);
    }

    public void delete(String problemId) {
        problemMapper.deleteByPrimaryKey(problemId);
    }

    public Problem findById(String problemId) {
        return problemMapper.selectByPrimaryKey(problemId);
    }

    public List<Problem> search(Problem problem) {
        ProblemExample example = new ProblemExample();
        ProblemExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(problem.getId())) {
            criteria.andIdEqualTo(problem.getId());
        }
        if (StringUtils.isNotBlank(problem.getTitle())) {
            criteria.andIdEqualTo(problem.getTitle());
        }
        if (StringUtils.isNotBlank(problem.getContent())) {
            criteria.andIdEqualTo(problem.getContent());
        }
        if (StringUtils.isNotBlank(problem.getUserid())) {
            criteria.andIdEqualTo(problem.getUserid());
        }
        if (StringUtils.isNotBlank(problem.getNickname())) {
            criteria.andIdEqualTo(problem.getNickname());
        }
        if (StringUtils.isNotBlank(problem.getSolve())) {
            criteria.andIdEqualTo(problem.getSolve());
        }
        if (StringUtils.isNotBlank(problem.getReplyname())) {
            criteria.andIdEqualTo(problem.getReplyname());
        }

        return problemMapper.selectByExample(example);
    }

    public PageResult<Problem> searchByPage(Problem problem, int page, int size) {
        PageHelper.startPage(page - 1, size);
        ProblemExample example = new ProblemExample();
        ProblemExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(problem.getId())) {
            criteria.andIdEqualTo(problem.getId());
        }
        if (StringUtils.isNotBlank(problem.getTitle())) {
            criteria.andIdEqualTo(problem.getTitle());
        }
        if (StringUtils.isNotBlank(problem.getContent())) {
            criteria.andIdEqualTo(problem.getContent());
        }
        if (StringUtils.isNotBlank(problem.getUserid())) {
            criteria.andIdEqualTo(problem.getUserid());
        }
        if (StringUtils.isNotBlank(problem.getNickname())) {
            criteria.andIdEqualTo(problem.getNickname());
        }
        if (StringUtils.isNotBlank(problem.getSolve())) {
            criteria.andIdEqualTo(problem.getSolve());
        }
        if (StringUtils.isNotBlank(problem.getReplyname())) {
            criteria.andIdEqualTo(problem.getReplyname());
        }

        List<Problem> problemList = problemMapper.selectByExample(example);
        PageInfo<Problem> pageInfo = new PageInfo<>(problemList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public PageResult<Problem> findNewListByLabelid(String labelid, int page, int size) {
        PageHelper.startPage(page - 1, size);
        List<Problem> problems;
        if(labelid.equals("0")){
            problems = problemMapper.selectByExample(new ProblemExample());
        }else{
            problems =  problemMapper.findNewListByLabelid(labelid);
        }
        PageInfo<Problem> pageInfo = new PageInfo<>(problems);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
    public PageResult<Problem> findHotListByLabelid(String labelid, int page, int size) {
        PageHelper.startPage(page - 1, size);
        List<Problem> problems;
        if(labelid.equals("0")){
            problems = problemMapper.selectByExample(new ProblemExample());
        }else{
            problems =  problemMapper.findHotListByLabelid(labelid);
        }
        PageInfo<Problem> pageInfo = new PageInfo<>(problems);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
    public PageResult<Problem> findWaitListByLabelid(String labelid, int page, int size) {
        PageHelper.startPage(page - 1, size);
        List<Problem> problems;
        if(labelid.equals("0")){
            problems = problemMapper.selectByExample(new ProblemExample());
        }else{
            problems =  problemMapper.findWaitListByLabelid(labelid);
        }
        PageInfo<Problem> pageInfo = new PageInfo<>(problems);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }


}
