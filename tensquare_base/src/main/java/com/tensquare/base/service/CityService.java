package com.tensquare.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.base.mapper.CityMapper;
import com.tensquare.base.pojo.City;
import com.tensquare.base.pojo.CityExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityService {

    @Autowired
    CityMapper cityMapper;

    @Autowired
    IdWorker idWorker;

    public List<City> findAll() {
        CityExample example = new CityExample();
        return cityMapper.selectByExample(example);
    }

    public void save(City city) {
        city.setId(idWorker.nextId()+"");
        cityMapper.insert(city);
    }

    public void update(City city) {
        cityMapper.updateByPrimaryKeySelective(city);
    }

    public void delete(String cityId) {
        cityMapper.deleteByPrimaryKey(cityId);
    }

    public City findById(String cityId) {
        return cityMapper.selectByPrimaryKey(cityId);
    }

    public List<City> search(City city) {
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(city.getId())) {
            criteria.andIdEqualTo(city.getId());
        }
        if(StringUtils.isNotBlank(city.getName())){
            criteria.andNameEqualTo(city.getName());
        }
        if (StringUtils.isNotBlank(city.getIshot())) {
            criteria.andIshotEqualTo(city.getIshot());
        }
        return cityMapper.selectByExample(example);
    }

    public PageResult<City> searchByPage(City city, int page, int size) {
        PageHelper.startPage(page-1,size);
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(city.getId())) {
            criteria.andIdEqualTo(city.getId());
        }
        if(StringUtils.isNotBlank(city.getName())){
            criteria.andNameEqualTo(city.getName());
        }
        if (StringUtils.isNotBlank(city.getIshot())) {
            criteria.andIshotEqualTo(city.getIshot());
        }
        List<City> cityList = cityMapper.selectByExample(example);
        PageInfo<City> pageInfo = new PageInfo<>(cityList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
