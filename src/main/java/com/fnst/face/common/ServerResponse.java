package com.fnst.face.common;

/**
 * @author Luyue
 * @date 2019/3/17 16:03
 **/
public class ServerResponse {
    private Integer code;
    private String message;
    private Object data;

    public ServerResponse() {
    }

    public ServerResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ServerResponse success() {
        ServerResponse result = new ServerResponse();
        result.setResultCode(ResponseCode.SUCCESS);
        return result;
    }

    public static ServerResponse success(Object data) {
        ServerResponse result = new ServerResponse();
        result.setResultCode(ResponseCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ServerResponse failure(String message) {
        ServerResponse result = new ServerResponse();
        result.setCode(ResponseCode.FAILURE.getCode());
        result.message = message;
        return result;
    }

    public static ServerResponse failure(ResponseCode resultCode) {
        ServerResponse result = new ServerResponse();
        result.setResultCode(resultCode);
        return result;
    }

    public static ServerResponse failure(ResponseCode resultCode, Object data) {
        ServerResponse result = new ServerResponse();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * 将TResultCode中的返回码和返回信息复制到TResult中
     *
     * @param resultCode
     */
    private void setResultCode(ResponseCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
