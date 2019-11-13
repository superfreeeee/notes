package cn.superfree.demo3.service.imp;

import cn.superfree.demo3.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GradeServiceImp implements GradeService {

  private Map<String, Integer> data;

  GradeServiceImp() {
    data = new HashMap<>();
    data.put("User_Math", 100);
    data.put("User_Chinese", 90);
    data.put("User_English", 80);
    data.put("User_Nature", 70);
    data.put("User_Social", 60);
    data.put("Admin_Math", 50);
    data.put("Admin_Chinese", 40);
    data.put("Admin_English", 30);
    data.put("Admin_Nature", 20);
    data.put("Admin_Social", 10);
  }

  @Override
  public Map<String, Integer> getGrades(String identity) {
    Map<String, Integer> result = new HashMap<>();
    for(Map.Entry<String, Integer> entry : data.entrySet()) {
      if(entry.getKey().startsWith(identity)) {
        result.put(entry.getKey(), entry.getValue());
      }
    }
    return result;
  }
}
