package com.zsls.controller;

import com.zsls.common.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/result")
    public ResultVO<Map<String, Object>> helloResultVO() {
        return ResultVO.success(INFO);
    }

    @GetMapping("/resultBody")
    public Map<String, Object> helloResultBody() {
        return INFO;
    }

    @GetMapping(value = "testInt")
    public Integer testInt() throws Exception {
        return 123;
    }

    @GetMapping(value = "testString")
    public String testString() throws Exception {
        return "123";
    }
}