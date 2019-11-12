package cn.superfree.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/")
  public String hello() {
    return "hello user";
  }

  @GetMapping("/login")
  public String login() {
    return "user request to login";
  }
}
