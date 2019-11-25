package cn.superfree.visualizationsimulation.constant;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Response {

  private int code;

  private String message;

  private Object data;

  private Map<String, String> rels = new HashMap<>();

  private Response(int code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static Response ok(String message, Object data) {
    return new Response(200, message, data);
  }

  public static Response ok(Object data) {
    return new Response(200, "success", data);
  }

  public static Response ok(String message) {
    return new Response(200, message, null);
  }

  public static Response error(String message, Object data) {
    return new Response(403, message, data);
  }

  public static Response error(Object data) {
    return new Response(403, "fail", data);
  }

  public static Response error(String message) {
    return new Response(403, message, null);
  }

  public void putRel(String key, String value) {
    rels.put(key, value);
  }
}
