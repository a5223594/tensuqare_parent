package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Reply;
import com.tensquare.qa.service.ReplyService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@CrossOrigin
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Reply> ReplyList = replyService.findAll();
        return new Result(true, StatusCode.OK, "success", ReplyList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Reply reply) {
        replyService.save(reply);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{replyId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Reply reply) {
        replyService.update(reply);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{replyId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String replyId) {
        replyService.delete(replyId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{replyId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String replyId) {
        Reply reply = replyService.findById(replyId);
        return new Result(true, StatusCode.OK, "success", reply);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Reply reply) {
        List<Reply> replyList = replyService.search(reply);
        return new Result(true, StatusCode.OK, "success", replyList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Reply reply) {
        PageResult<Reply> pageResult = replyService.searchByPage(reply, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }





}
