package cn.superfree.demo3.entity;

import cn.superfree.demo3.constant.param.UserParam;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEntity {

  private Integer id;

  private String username;

  private String password;

  public UserEntity(UserParam userParam) {
    this.username = userParam.getUsername();
    this.password = userParam.getPassword();
  }
}
