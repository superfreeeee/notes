package cn.superfree.demo3.service.imp;

import cn.superfree.demo3.constant.param.UserParam;
import cn.superfree.demo3.constant.response.SimpleResponse;
import cn.superfree.demo3.dao.UserMapper;
import cn.superfree.demo3.entity.UserEntity;
import cn.superfree.demo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public SimpleResponse register(UserParam userParam) {
    try {
      UserEntity userEntity = userMapper.queryUserByUsername(userParam.getUsername());
      if(userEntity != null) {
        return SimpleResponse.exception("username already existed");
      }
      userMapper.insert(new UserEntity(userParam));
      return SimpleResponse.ok("register success");
    } catch (Exception e) {
      e.printStackTrace();
      return SimpleResponse.error("server error");
    }
  }

  @Override
  public SimpleResponse login(UserParam userParam) {
    try {
      UserEntity userEntity = userMapper.queryUserByUsername(userParam.getUsername());
      if(userEntity == null) {
        return SimpleResponse.exception("username not found");
      }
      if(!userEntity.getPassword().equals(userParam.getPassword())) {
        return SimpleResponse.exception("wrong password");
      }
      return SimpleResponse.ok(userEntity);
    } catch (Exception e) {
      e.printStackTrace();
      return SimpleResponse.error("server error");
    }
  }

}
