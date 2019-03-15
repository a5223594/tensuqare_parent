package com.tensquare.article.controller;

import com.tensquare.article.pojo.Column;
import com.tensquare.article.service.ColumnService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/column")
@CrossOrigin
public class ColumnController {

    @Autowired
    ColumnService columnService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Column> ColumnList = columnService.findAll();
        return new Result(true, StatusCode.OK, "success", ColumnList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Column column) {
        columnService.save(column);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{columnId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Column column,@PathVariable String columnId) {
        column.setId(columnId);
        columnService.update(column);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{columnId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String columnId) {
        columnService.delete(columnId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{columnId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String columnId) {
        Column column = columnService.findById(columnId);
        return new Result(true, StatusCode.OK, "success", column);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Column column) {
        List<Column> columnList = columnService.search(column);
        return new Result(true, StatusCode.OK, "success", columnList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Column column) {
        PageResult<Column> pageResult = columnService.searchByPage(column, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
