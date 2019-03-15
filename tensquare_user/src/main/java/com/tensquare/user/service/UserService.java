package com.tensquare.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.user.mapper.UserMapper;
import com.tensquare.user.pojo.User;
import com.tensquare.user.pojo.UserExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IdWorker idWorker;

    public List<User> findAll() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    public void save(User user) {
        user.setId(idWorker.nextId()+"");
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void delete(String userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    public User findById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<User> search(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(user.getId())) {
               criteria.andIdEqualTo(user.getId());
            }
          	if (StringUtils.isNotBlank(user.getMobile())) {
               criteria.andIdEqualTo(user.getMobile());
            }
          	if (StringUtils.isNotBlank(user.getPassword())) {
               criteria.andIdEqualTo(user.getPassword());
            }
          	if (StringUtils.isNotBlank(user.getNickname())) {
               criteria.andIdEqualTo(user.getNickname());
            }
          	if (StringUtils.isNotBlank(user.getSex())) {
               criteria.andIdEqualTo(user.getSex());
            }
          	if (StringUtils.isNotBlank(user.getAvatar())) {
               criteria.andIdEqualTo(user.getAvatar());
            }
          	if (StringUtils.isNotBlank(user.getEmail())) {
               criteria.andIdEqualTo(user.getEmail());
            }
          	if (StringUtils.isNotBlank(user.getInterest())) {
               criteria.andIdEqualTo(user.getInterest());
            }
          	if (StringUtils.isNotBlank(user.getPersonality())) {
               criteria.andIdEqualTo(user.getPersonality());
            }

        return userMapper.selectByExample(example);
    }

    public PageResult<User> searchByPage(User user, int page, int size) {
        PageHelper.startPage(page-1,size);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(user.getId())) {
               criteria.andIdEqualTo(user.getId());
            }
          	if (StringUtils.isNotBlank(user.getMobile())) {
               criteria.andIdEqualTo(user.getMobile());
            }
          	if (StringUtils.isNotBlank(user.getPassword())) {
               criteria.andIdEqualTo(user.getPassword());
            }
          	if (StringUtils.isNotBlank(user.getNickname())) {
               criteria.andIdEqualTo(user.getNickname());
            }
          	if (StringUtils.isNotBlank(user.getSex())) {
               criteria.andIdEqualTo(user.getSex());
            }
          	if (StringUtils.isNotBlank(user.getAvatar())) {
               criteria.andIdEqualTo(user.getAvatar());
            }
          	if (StringUtils.isNotBlank(user.getEmail())) {
               criteria.andIdEqualTo(user.getEmail());
            }
          	if (StringUtils.isNotBlank(user.getInterest())) {
               criteria.andIdEqualTo(user.getInterest());
            }
          	if (StringUtils.isNotBlank(user.getPersonality())) {
               criteria.andIdEqualTo(user.getPersonality());
            }

        List<User> userList = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
