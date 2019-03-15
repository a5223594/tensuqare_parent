package com.tensquare.base.controller;

import com.tensquare.base.pojo.City;
import com.tensquare.base.service.CityService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<City> CityList = cityService.findAll();
        return new Result(true, StatusCode.OK, "success", CityList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody City city) {
        cityService.save(city);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{cityId}",method = RequestMethod.PUT)
    public Result update(@RequestBody City city) {
        cityService.update(city);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String cityId) {
        cityService.delete(cityId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String cityId) {
        City city = cityService.findById(cityId);
        return new Result(true, StatusCode.OK, "success", city);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody City city) {
        List<City> cityList = cityService.search(city);
        return new Result(true, StatusCode.OK, "success", cityList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,City city) {
        PageResult<City> pageResult = cityService.searchByPage(city, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
