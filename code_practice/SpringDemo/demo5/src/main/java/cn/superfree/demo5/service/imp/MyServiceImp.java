package cn.superfree.demo5.service.imp;

import cn.superfree.demo5.entity.User;
import cn.superfree.demo5.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImp implements MyService {

  @Autowired
  RedisTemplate redisTemplate;

  @Override
  public boolean test() {
    try {
      ValueOperations valueOperations = redisTemplate.opsForValue();
      valueOperations.set("k1", "v1");
      Object k1 = valueOperations.get("k1");
      System.out.println(k1);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean setUser(User user) {
    try {
      ListOperations listOperations = redisTemplate.opsForList();
      listOperations.leftPush("users", user);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<User> getUsers() {
    try {
      ListOperations listOperations = redisTemplate.opsForList();
      long len = listOperations.size("users");
      List list = listOperations.range("users", 0, len);
      System.out.println(list);
      return list;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
