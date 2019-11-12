package cn.superfree.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @GetMapping("/")
  public String hello() {
    return "hello admin";
  }

  @GetMapping("/login")
  public String login() {
    return "admin request to login";
  }
}
