package cn.superfree.demo4.constant.response;

import lombok.Data;

@Data
public class Response {

  private int code;

  private String status;

  private Object data;

  private Response(int code, String status, Object data) {
    this.code = code;
    this.status = status;
    this.data = data;
  }

  public static Response ok(Object data) {
    return new Response(200, "success", data);
  }

  public static Response error(Object data) {
    return new Response(400, "error", data);
  }
}
