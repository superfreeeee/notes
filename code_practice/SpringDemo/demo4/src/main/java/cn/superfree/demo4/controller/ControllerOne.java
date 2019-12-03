package cn.superfree.demo4.controller;

import cn.superfree.demo4.constant.param.UserParam;
import cn.superfree.demo4.constant.response.Response;
import cn.superfree.demo4.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/one")
@Api(tags = "controller 1")
public class ControllerOne {

    @GetMapping("/")
    @ApiOperation("hello world")
    public String hello() {
        return "hello world";
    }

    @PostMapping("/test1")
    @ApiOperation("function test1()")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", defaultValue = "superfree"),
            @ApiImplicitParam(name = "age", value = "年齡", defaultValue = "18", required = true)
    })
    public Response test1(String name, @RequestParam(required = true) int age) {
        String result = "I am " + name + ", and I am " + age + " years old.\n";
        result += "I have almost " + (80 - age) + " to live.\n";
        return Response.ok(result);
    }

    @GetMapping("/test2/{id}")
    @ApiOperation("查詢id")
    @ApiImplicitParam(name = "id", value = "用戶id")
    public Response test2(@PathVariable int id) {
        String result = "search for userId: " + id;
        return Response.ok(result);
    }

    @PutMapping("/test3")
    @ApiOperation("根據id更新用戶的接口，不存在就建立新用戶")
    public Response test3(@RequestBody UserParam userParam) {
        return Response.ok(new User(userParam));
    }
}
