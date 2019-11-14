package cn.superfree.demo5.controller;

import cn.superfree.demo5.entity.User;
import cn.superfree.demo5.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

  @Autowired
  MyService myService;

  @GetMapping("/")
  public boolean test() {
    return myService.test();
  }

  @GetMapping("/register")
  public List<User> register(int id, String username) {
    boolean setOperation = myService.setUser(new User(id, username));
    if(setOperation) {
      System.out.println("set key success");
    } else {
      System.out.println("set key fail");
    }
    return myService.getUsers();
  }
}
