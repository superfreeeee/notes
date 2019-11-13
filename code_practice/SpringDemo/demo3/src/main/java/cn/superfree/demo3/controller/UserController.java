package cn.superfree.demo3.controller;

import cn.superfree.demo3.constant.param.UserParam;
import cn.superfree.demo3.constant.response.SimpleResponse;
import cn.superfree.demo3.entity.UserEntity;
import cn.superfree.demo3.service.GradeService;
import cn.superfree.demo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  GradeService gradeService;

  @Autowired
  UserService userService;

  @GetMapping("/")
  public SimpleResponse hello() {
    String data = "hello user";
    return SimpleResponse.ok(data);
  }

  @GetMapping("/login")
  public SimpleResponse login() {
    String data = "user request to login";
    return SimpleResponse.ok(data);
  }

  @GetMapping("/grades")
  public SimpleResponse calculate() {
    Map<String, Integer> data = gradeService.getGrades("User");
    return SimpleResponse.ok(data);
  }

  @PostMapping("/register")
  public SimpleResponse register(@RequestBody UserParam userParam) {
    return userService.register(userParam);
  }

  @PostMapping("/login")
  public SimpleResponse login(@RequestBody UserParam userParam) {
    return userService.login(userParam);
  }

}
