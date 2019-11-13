package cn.superfree.demo3.dao;

import cn.superfree.demo3.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

  UserEntity queryUserByUsername(String username);

  void insert(UserEntity userEntity);
}
