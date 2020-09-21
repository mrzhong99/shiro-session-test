package com.zhongpeiqi.exception;

import com.zhongpeiqi.common.code.ResultCode;

public class CustomException extends RuntimeException{
    private final ResultCode resultCode;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public CustomException(ResultCode resultCode) {
        super("错误代码: " + resultCode.errorCode() + "错误信息：" + resultCode.errorDesc());
        this.resultCode = resultCode;
    }
}
