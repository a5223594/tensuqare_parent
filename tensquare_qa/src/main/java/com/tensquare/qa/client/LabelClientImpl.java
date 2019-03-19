package com.tensquare.qa.client;

import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String labelId) {
        System.out.println("熔断器启动了");
        return new Result(false, StatusCode.ERROR,"检查Base服务是否正常");
    }
}
