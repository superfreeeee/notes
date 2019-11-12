package cn.superfree.demo2.constant.response;

import lombok.Data;

@Data
public class SimpleResponse {

  private static final int SUCCESS = 200;

  private static final int ERROR = 403;

  private static final int CATCH_EXCEPTION = 2500;

  private int code;

  private Object data;

  private SimpleResponse(int code, Object data) {
    this.code = code;
    this.data = data;
  }

  public static SimpleResponse ok(Object data) {
    return new SimpleResponse(SUCCESS, data);
  }

  public static SimpleResponse error(Object data) {
    return new SimpleResponse(ERROR, data);
  }

  public static SimpleResponse exception(Object data) {
    return new SimpleResponse(CATCH_EXCEPTION, data);
  }

  public static SimpleResponse other(int code, Object data) {
    return new SimpleResponse(code, data);
  }

}
