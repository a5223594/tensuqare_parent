package com.tensquare.qa.client;

import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tensquare-base",fallback = LabelClientImpl.class)
public interface LabelClient {

    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
     Result findById(@PathVariable("labelId") String labelId);

}
