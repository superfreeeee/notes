package cn.superfree.demo6.constant;

import lombok.Data;

@Data
public class Response {

  private int code;

  private String message;

  private Object data;

  private Response(int code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static Response ok(String message, Object data) {
    return new Response(200, message, data);
  }

  public static Response ok(String message) {
    return ok(message, null);
  }

  public static Response error(String message, Object data) {
    return new Response(2500, message, data);
  }

  public static Response error(String message) {
    return error(message, null);
  }
}
