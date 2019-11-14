package cn.superfree.demo4.entity;

import cn.superfree.demo4.constant.param.UserParam;
import lombok.Data;

@Data
public class User {

  private static int counter = 0;

  private int id;

  private String name;

  private Integer age;

  public User() {
    id = ++counter;
  }

  public User(UserParam userParam) {
    this();
    this.name = userParam.getName();
    this.age = userParam.getAge();
  }
}
