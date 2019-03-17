package com.tensquare.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.user.mapper.UserMapper;
import com.tensquare.user.pojo.AdminExample;
import com.tensquare.user.pojo.User;
import com.tensquare.user.pojo.UserExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IdWorker idWorker;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    BCryptPasswordEncoder encoder;

    public void sendSms(String mobile){
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        int code = random.nextInt(max);
        if(code<min){
            code +=min;
        }
        System.out.println(mobile+"验证码:"+code);
        redisTemplate.opsForValue().set("smscode_"+mobile,code+"", 5,TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code+"");
        rabbitTemplate.convertAndSend("sms",map);
    }

    public void add(User user,String code){
        String syscode = (String) redisTemplate.opsForValue().get("smscode_"+user.getMobile());
        if (syscode == null) {
            throw new RuntimeException("请点击获取验证码");
        }
        if(!syscode.equals(code)){
            throw new RuntimeException("验证码不正确");
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(user.getMobile());
        List<User> users = userMapper.selectByExample(example);
        if(users!=null && users.size()>0){
            throw new RuntimeException("手机号不能重复注册");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(idWorker.nextId()+"");
        user.setFanscount(0);
        user.setFollowcount(0);
        user.setOnline(0L);
        user.setUpdatedate(new Date());
        user.setRegdate(new Date());
        user.setLastdate(new Date());
        userMapper.insert(user);
    }

    public User findByMobileAndPassword(String mobile, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        User user = userMapper.selectByExample(example).get(0);
        if (user != null && encoder.matches(password,user.getPassword())) {
            return user;
        }else
            return null;
    }

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
