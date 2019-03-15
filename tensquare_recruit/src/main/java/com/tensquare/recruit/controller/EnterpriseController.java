package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enterprise")
@CrossOrigin
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Enterprise> EnterpriseList = enterpriseService.findAll();
        return new Result(true, StatusCode.OK, "success", EnterpriseList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Enterprise enterprise) {
        enterpriseService.save(enterprise);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{enterpriseId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Enterprise enterprise) {
        enterpriseService.update(enterprise);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{enterpriseId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String enterpriseId) {
        enterpriseService.delete(enterpriseId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{enterpriseId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String enterpriseId) {
        Enterprise enterprise = enterpriseService.findById(enterpriseId);
        return new Result(true, StatusCode.OK, "success", enterprise);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Enterprise enterprise) {
        List<Enterprise> enterpriseList = enterpriseService.search(enterprise);
        return new Result(true, StatusCode.OK, "success", enterpriseList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Enterprise enterprise) {
        PageResult<Enterprise> pageResult = enterpriseService.searchByPage(enterprise, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @RequestMapping(value="/search/hotlist",method = RequestMethod.GET)
    public Result findHotList(){
        return new Result(true,StatusCode.OK,"热门列表",enterpriseService.findHotList());
    }





}
