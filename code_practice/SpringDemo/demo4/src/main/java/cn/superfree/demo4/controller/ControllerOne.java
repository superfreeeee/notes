package cn.superfree.demo4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/one")
public class ControllerOne {

  @GetMapping("/")
  public String hello() {
    return "hello world";
  }
}
