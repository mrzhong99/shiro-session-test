package com.zhongpeiqi.common.response;

import com.zhongpeiqi.common.code.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements Response {
    String code = SUCCESS_CODE;
    String errorCode;
    String errorDesc;
    Object data;

    public ResponseResult(ResultCode resultCode) {
        this.code = resultCode.code();
        this.errorCode = resultCode.errorCode();
        this.errorDesc = resultCode.errorDesc();
    }

    public ResponseResult(String code, String errorCode, String errorDesc, Object data) {
        this.code = code;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.data = data;
    }

    public ResponseResult(String code) {
        this.code = code;
    }

    public ResponseResult(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(Response.SUCCESS_CODE);
    }

    public static ResponseResult SUCCESS(Object data) {
        return new ResponseResult(Response.SUCCESS_CODE, data);
    }

    public static ResponseResult FAIL(ResultCode resultCode) {
        return new ResponseResult(resultCode);
    }

    public static ResponseResult FAIL(String code, String errorCode, String errorDesc, Object data) {
        return new ResponseResult(code, errorCode, errorDesc, data);
    }
}
