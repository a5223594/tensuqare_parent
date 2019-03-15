package com.tensquare.article.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.article.mapper.ColumnMapper;
import com.tensquare.article.pojo.Column;
import com.tensquare.article.pojo.ColumnExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ColumnService {

    @Autowired
    ColumnMapper columnMapper;

    @Autowired
    IdWorker idWorker;

    public List<Column> findAll() {
        ColumnExample example = new ColumnExample();
        return columnMapper.selectByExample(example);
    }

    public void save(Column column) {
        column.setId(idWorker.nextId()+"");
        columnMapper.insert(column);
    }

    public void update(Column column) {
        columnMapper.updateByPrimaryKeySelective(column);
    }

    public void delete(String columnId) {
        columnMapper.deleteByPrimaryKey(columnId);
    }

    public Column findById(String columnId) {
        return columnMapper.selectByPrimaryKey(columnId);
    }

    public List<Column> search(Column column) {
        ColumnExample example = new ColumnExample();
        ColumnExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(column.getId())) {
               criteria.andIdEqualTo(column.getId());
            }
          	if (StringUtils.isNotBlank(column.getName())) {
               criteria.andIdEqualTo(column.getName());
            }
          	if (StringUtils.isNotBlank(column.getSummary())) {
               criteria.andIdEqualTo(column.getSummary());
            }
          	if (StringUtils.isNotBlank(column.getUserid())) {
               criteria.andIdEqualTo(column.getUserid());
            }
          	if (StringUtils.isNotBlank(column.getState())) {
               criteria.andIdEqualTo(column.getState());
            }

        return columnMapper.selectByExample(example);
    }

    public PageResult<Column> searchByPage(Column column, int page, int size) {
        PageHelper.startPage(page-1,size);
        ColumnExample example = new ColumnExample();
        ColumnExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(column.getId())) {
               criteria.andIdEqualTo(column.getId());
            }
          	if (StringUtils.isNotBlank(column.getName())) {
               criteria.andIdEqualTo(column.getName());
            }
          	if (StringUtils.isNotBlank(column.getSummary())) {
               criteria.andIdEqualTo(column.getSummary());
            }
          	if (StringUtils.isNotBlank(column.getUserid())) {
               criteria.andIdEqualTo(column.getUserid());
            }
          	if (StringUtils.isNotBlank(column.getState())) {
               criteria.andIdEqualTo(column.getState());
            }

        List<Column> columnList = columnMapper.selectByExample(example);
        PageInfo<Column> pageInfo = new PageInfo<>(columnList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
