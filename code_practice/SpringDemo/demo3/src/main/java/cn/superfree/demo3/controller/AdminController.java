package cn.superfree.demo3.controller;

import cn.superfree.demo3.constant.response.SimpleResponse;
import cn.superfree.demo3.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  GradeService gradeService;

  @GetMapping("/")
  public SimpleResponse hello() {
    String message = "hello admin";
    return SimpleResponse.ok(message);
  }

  @GetMapping("/login")
  public SimpleResponse login() {
    String message = "admin request to login";
    return SimpleResponse.ok(message);
  }

  @GetMapping("/grades")
  public SimpleResponse calculate() {
    Map<String, Integer> data = gradeService.getGrades("Admin");
    return SimpleResponse.ok(data);
  }
}
