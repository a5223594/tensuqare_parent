package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
@CrossOrigin
public class RecruitController {

    @Autowired
    RecruitService recruitService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Recruit> RecruitList = recruitService.findAll();
        return new Result(true, StatusCode.OK, "success", RecruitList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Recruit recruit) {
        recruitService.save(recruit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{recruitId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Recruit recruit) {
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{recruitId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String recruitId) {
        recruitService.delete(recruitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{recruitId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String recruitId) {
        Recruit recruit = recruitService.findById(recruitId);
        return new Result(true, StatusCode.OK, "success", recruit);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Recruit recruit) {
        List<Recruit> recruitList = recruitService.search(recruit);
        return new Result(true, StatusCode.OK, "success", recruitList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Recruit recruit) {
        PageResult<Recruit> pageResult = recruitService.searchByPage(recruit, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }
    @RequestMapping(value = "/search/recommend",method = RequestMethod.GET)
    public Result findTop4ByStateOrderByCreatetimeDesc(){
        return new Result(true,StatusCode.OK,"推荐职位",recruitService.findTop4ByStateOrderByCreatetimeDesc("2"));
    }

    @RequestMapping(value = "/search/newlist",method = RequestMethod.GET)
    public Result findTop12ByStateOrderByCreatetimeDesc(){
        return new Result(true,StatusCode.OK,"最新职位",recruitService.findTop12ByStateOrderByCreatetimeDesc("0"));
    }





}
