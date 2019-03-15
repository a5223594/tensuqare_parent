package com.tensquare.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.base.mapper.LabelMapper;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.pojo.LabelExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    IdWorker idWorker;

    public List<Label> findAll() {
        LabelExample example = new LabelExample();
        return labelMapper.selectByExample(example);
    }

    public void save(Label label) {
        label.setId(idWorker.nextId()+"");
        labelMapper.insert(label);
    }

    public void update(Label label) {
        labelMapper.updateByPrimaryKeySelective(label);
    }

    public void delete(String labelId) {
        labelMapper.deleteByPrimaryKey(labelId);
    }

    public Label findById(String labelId) {
        return labelMapper.selectByPrimaryKey(labelId);
    }

    public List<Label> search(Label label) {
        LabelExample example = new LabelExample();
        LabelExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(label.getId())) {
            criteria.andIdEqualTo(label.getId());
        }
        if(StringUtils.isNotBlank(label.getLabelname())){
            criteria.andLabelnameEqualTo(label.getLabelname());
        }
        if (StringUtils.isNotBlank(label.getState())) {
            criteria.andStateEqualTo(label.getState());
        }
        if (StringUtils.isNotBlank(label.getState())) {
            criteria.andStateEqualTo(label.getState());
        }
        return labelMapper.selectByExample(example);
    }

    public PageResult<Label> searchByPage(Label label, int page, int size) {
        PageHelper.startPage(page-1,size);
        LabelExample example = new LabelExample();
        LabelExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(label.getId())) {
            criteria.andIdEqualTo(label.getId());
        }
        if(StringUtils.isNotBlank(label.getLabelname())){
            criteria.andLabelnameEqualTo(label.getLabelname());
        }
        if (StringUtils.isNotBlank(label.getState())) {
            criteria.andStateEqualTo(label.getState());
        }
        if (StringUtils.isNotBlank(label.getState())) {
            criteria.andStateEqualTo(label.getState());
        }
        List<Label> labelList = labelMapper.selectByExample(example);
        PageInfo<Label> pageInfo = new PageInfo<>(labelList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
