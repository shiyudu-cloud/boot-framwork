package com.eric.web;

import com.eric.aop.annotation.ApiLog;
import com.eric.aop.collector.LogCollector;
import com.eric.aop.model.AopLogData;
import com.eric.response.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shiyu.du
 * @date 2020/9/8
 **/

@RestController
public class HelloController implements LogCollector {


    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    @ApiLog(type = "测试",stackTraceOnError = true,asyncMode= false)
    public Map<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/result")
    @ResponseBody
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }


    @GetMapping("/iframe")
    @ResponseBody
    public String testIframe(@RequestParam String token){
        return token;
    }

    @Override
    public void collect(AopLogData logData) {
        System.out.println(logData.toString());
    }
}
