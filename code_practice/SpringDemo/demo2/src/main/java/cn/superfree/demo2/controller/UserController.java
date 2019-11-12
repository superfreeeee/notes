package cn.superfree.demo2.controller;

import cn.superfree.demo2.constant.response.SimpleResponse;
import cn.superfree.demo2.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  GradeService gradeService;

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
}
