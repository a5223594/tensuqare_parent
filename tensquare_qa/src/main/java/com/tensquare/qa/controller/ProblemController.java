package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problem")
@CrossOrigin
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Problem> ProblemList = problemService.findAll();
        return new Result(true, StatusCode.OK, "success", ProblemList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Problem problem) {
        problemService.save(problem);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{problemId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Problem problem,@PathVariable String problemId) {
        problem.setId(problemId);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{problemId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String problemId) {
        problemService.delete(problemId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{problemId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String problemId) {
        Problem problem = problemService.findById(problemId);
        return new Result(true, StatusCode.OK, "success", problem);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Problem problem) {
        List<Problem> problemList = problemService.search(problem);
        return new Result(true, StatusCode.OK, "success", problemList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Problem problem) {
        PageResult<Problem> pageResult = problemService.searchByPage(problem, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result findNewListByLabelid(@PathVariable String labelid,@PathVariable int page,@PathVariable int size){
        PageResult<Problem> pageResult = problemService.findNewListByLabelid(labelid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result findHotListByLabelid(@PathVariable String labelid,@PathVariable int page,@PathVariable int size){
        PageResult<Problem> pageResult = problemService.findHotListByLabelid(labelid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
    @RequestMapping(value = "/waitlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result findWaitListByLabelid(@PathVariable String labelid,@PathVariable int page,@PathVariable int size){
        PageResult<Problem> pageResult = problemService.findWaitListByLabelid(labelid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }



}
