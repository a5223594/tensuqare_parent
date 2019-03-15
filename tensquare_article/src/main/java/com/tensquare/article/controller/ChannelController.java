package com.tensquare.article.controller;

import com.tensquare.article.pojo.Channel;
import com.tensquare.article.service.ChannelService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
@CrossOrigin
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Channel> ChannelList = channelService.findAll();
        return new Result(true, StatusCode.OK, "success", ChannelList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Channel channel) {
        channelService.save(channel);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{channelId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Channel channel,@PathVariable String channelId) {
        channel.setId(channelId);
        channelService.update(channel);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{channelId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String channelId) {
        channelService.delete(channelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{channelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String channelId) {
        Channel channel = channelService.findById(channelId);
        return new Result(true, StatusCode.OK, "success", channel);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Channel channel) {
        List<Channel> channelList = channelService.search(channel);
        return new Result(true, StatusCode.OK, "success", channelList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Channel channel) {
        PageResult<Channel> pageResult = channelService.searchByPage(channel, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
