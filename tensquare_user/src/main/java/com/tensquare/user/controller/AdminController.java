package com.tensquare.user.controller;

import com.tensquare.common.util.JwtUtil;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Admin> AdminList = adminService.findAll();
        return new Result(true, StatusCode.OK, "success", AdminList);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> map) {
        Admin admin = adminService.findByLoginnameAndPassword(map.get("loginname"), map.get("password"));
        if (admin != null) {
            Map<String,String> resultMap = new HashMap<>();
            String token = jwtUtil.createJWT(admin.getId(), admin.getLoginname(), "admin");
            resultMap.put("token", token);
            resultMap.put("name", admin.getLoginname());
            return new Result(true, StatusCode.OK, "登录成功",resultMap);
        }else{
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Admin admin) {
        adminService.save(admin);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{adminId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Admin admin,@PathVariable String adminId) {
        admin.setId(adminId);
        adminService.update(admin);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{adminId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String adminId) {
        adminService.delete(adminId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{adminId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String adminId) {
        Admin admin = adminService.findById(adminId);
        return new Result(true, StatusCode.OK, "success", admin);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Admin admin) {
        List<Admin> adminList = adminService.search(admin);
        return new Result(true, StatusCode.OK, "success", adminList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Admin admin) {
        PageResult<Admin> pageResult = adminService.searchByPage(admin, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
