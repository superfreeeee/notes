package cn.superfree.demo3.service;

import cn.superfree.demo3.constant.param.UserParam;
import cn.superfree.demo3.constant.response.SimpleResponse;
import cn.superfree.demo3.entity.UserEntity;

public interface UserService {

  SimpleResponse register(UserParam userParam);

  SimpleResponse login(UserParam userParam);
}
