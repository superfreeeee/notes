package cn.superfree.demo5.service;

import cn.superfree.demo5.entity.User;

import java.util.List;

public interface MyService {

  boolean test();

  boolean setUser(User user);

  List<User> getUsers();
}
