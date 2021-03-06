package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("tensquare-user")
public interface UserClient {

    @RequestMapping(value = "/user/incfans/{userid}/{x}",method = RequestMethod.POST)
    void incFansCount(@PathVariable("userid") String userid, @PathVariable("x") int x);

    @RequestMapping(value = "/user/incfollow/{userid}/{x}",method = RequestMethod.POST)
    void incFollowcount(@PathVariable("userid") String userid, @PathVariable("x") int x);
}
