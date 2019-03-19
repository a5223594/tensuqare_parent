package com.tensquare.user.controller;

import com.tensquare.common.util.JwtUtil;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(value = "/incfans/{userid}/{x}",method = RequestMethod.POST)
    public void incFansCount(@PathVariable String userid, @PathVariable int x) {
        userService.incFanscount(userid,x);
    }
    @RequestMapping(value = "/incfollow/{userid}/{x}",method = RequestMethod.POST)
    public void incFollowcount(@PathVariable String userid, @PathVariable int x) {
        userService.incFollowcount(userid,x);
    }

    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result register(@RequestBody User user, @PathVariable String code) {
        userService.add(user,code);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> map) {
        User user = userService.findByMobileAndPassword(map.get("mobile"), map.get("password"));
        if (user != null) {
            String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
            Map<String,String> resultMap = new HashMap<>();
            resultMap.put("token", token);
            resultMap.put("name", user.getNickname());
            resultMap.put("avatar",user.getAvatar());
            return new Result(true, StatusCode.OK, "登录成功",resultMap);
        }else{
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }

    }

    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendsms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功");
    }

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
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "权限不足");
        }
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
