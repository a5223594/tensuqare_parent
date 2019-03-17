package com.tensquare.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.user.mapper.AdminMapper;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.pojo.AdminExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    IdWorker idWorker;

    @Autowired
    BCryptPasswordEncoder encoder;

    public List<Admin> findAll() {
        AdminExample example = new AdminExample();
        return adminMapper.selectByExample(example);
    }

    public void save(Admin admin) {
        admin.setId(idWorker.nextId()+"");
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminMapper.insert(admin);
    }

    public Admin findByLoginnameAndPassword(String loginname, String password) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(loginname);
        Admin admin = adminMapper.selectByExample(example).get(0);
        if (admin != null && encoder.matches(password, admin.getPassword())) {
            return admin;
        }else{
            return null;
        }
    }

    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void delete(String adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    public Admin findById(String adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    public List<Admin> search(Admin admin) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(admin.getId())) {
               criteria.andIdEqualTo(admin.getId());
            }
          	if (StringUtils.isNotBlank(admin.getLoginname())) {
               criteria.andIdEqualTo(admin.getLoginname());
            }
          	if (StringUtils.isNotBlank(admin.getPassword())) {
               criteria.andIdEqualTo(admin.getPassword());
            }
          	if (StringUtils.isNotBlank(admin.getState())) {
               criteria.andIdEqualTo(admin.getState());
            }

        return adminMapper.selectByExample(example);
    }

    public PageResult<Admin> searchByPage(Admin admin, int page, int size) {
        PageHelper.startPage(page-1,size);
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(admin.getId())) {
               criteria.andIdEqualTo(admin.getId());
            }
          	if (StringUtils.isNotBlank(admin.getLoginname())) {
               criteria.andIdEqualTo(admin.getLoginname());
            }
          	if (StringUtils.isNotBlank(admin.getPassword())) {
               criteria.andIdEqualTo(admin.getPassword());
            }
          	if (StringUtils.isNotBlank(admin.getState())) {
               criteria.andIdEqualTo(admin.getState());
            }

        List<Admin> adminList = adminMapper.selectByExample(example);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
