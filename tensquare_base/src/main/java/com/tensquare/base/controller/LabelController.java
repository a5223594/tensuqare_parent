package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {



    @Autowired
    LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Label> LabelList = labelService.findAll();
        return new Result(true, StatusCode.OK, "success", LabelList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{labelId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label,@PathVariable String labelId) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId) {
        labelService.delete(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId) {
        Label label = labelService.findById(labelId);
        return new Result(true, StatusCode.OK, "success", label);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Label label) {
        List<Label> labelList = labelService.search(label);
        return new Result(true, StatusCode.OK, "success", labelList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Label label) {
        PageResult<Label> pageResult = labelService.searchByPage(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
