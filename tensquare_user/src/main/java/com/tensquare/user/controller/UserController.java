package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<User> UserList = userService.findAll();
        return new Result(true, StatusCode.OK, "success", UserList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody User user) {
        userService.save(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{userId}",method = RequestMethod.PUT)
    public Result update(@RequestBody User user,@PathVariable String userId) {
        user.setId(userId);
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String userId) {
        userService.delete(userId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String userId) {
        User user = userService.findById(userId);
        return new Result(true, StatusCode.OK, "success", user);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody User user) {
        List<User> userList = userService.search(user);
        return new Result(true, StatusCode.OK, "success", userList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,User user) {
        PageResult<User> pageResult = userService.searchByPage(user, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
