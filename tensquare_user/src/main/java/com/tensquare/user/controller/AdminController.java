package com.tensquare.user.controller;

import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Admin> AdminList = adminService.findAll();
        return new Result(true, StatusCode.OK, "success", AdminList);
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
