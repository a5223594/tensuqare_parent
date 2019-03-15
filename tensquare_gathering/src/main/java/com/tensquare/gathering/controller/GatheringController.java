package com.tensquare.gathering.controller;

import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.pojo.GatheringWithBLOBs;
import com.tensquare.gathering.service.GatheringService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gathering")
@CrossOrigin
public class GatheringController {

    @Autowired
    GatheringService gatheringService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Gathering> GatheringList = gatheringService.findAll();
        return new Result(true, StatusCode.OK, "success", GatheringList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody GatheringWithBLOBs gathering) {
        gatheringService.save(gathering);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{gatheringId}",method = RequestMethod.PUT)
    public Result update(@RequestBody GatheringWithBLOBs gathering) {
        gatheringService.update(gathering);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{gatheringId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String gatheringId) {
        gatheringService.delete(gatheringId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{gatheringId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String gatheringId) {
        Gathering gathering = gatheringService.findById(gatheringId);
        return new Result(true, StatusCode.OK, "success", gathering);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Gathering gathering) {
        List<Gathering> gatheringList = gatheringService.search(gathering);
        return new Result(true, StatusCode.OK, "success", gatheringList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Gathering gathering) {
        PageResult<Gathering> pageResult = gatheringService.searchByPage(gathering, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
