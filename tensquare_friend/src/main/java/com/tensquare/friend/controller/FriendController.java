package com.tensquare.friend.controller;

import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserClient userClient;

    /**
     * 添加对方为好友 即关注对方，自身关注数加1，对方粉丝数加1
     * @param friendid
     * @param type 1喜欢 0不喜欢
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {

        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        String userid = claims.getId();
        if (type.equals("1")) {
            if(friendService.addFriend(userid,friendid)==0){
                return new Result(false, StatusCode.REPERROR, "已经是您的好友");
            }
            userClient.incFansCount(friendid,1);
            userClient.incFollowcount(userid,1);
        } else {
            friendService.addNoFriend(userid,friendid);
            deleteFriend(friendid);
            userClient.incFollowcount(userid,-1);
            userClient.incFansCount(friendid,-1);
        }
        return new Result(true, StatusCode.OK, "操作成功");
    }

    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权操作");
        }
        String userid = claims.getId();
        friendService.deleteFriend(userid,friendid);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
