package com.zhongpeiqi.common.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CommonCode implements ResultCode{
    NOT_LOGIN("1","10001","您还未登录，请先登录"),
    AUTHENTICATE_FAIL("1","10002","认证失败，账号或密码错误"),
    UNAUTHORIZED("1","10003","您没有此权限，请联系管理员"),
    OTHER_EXCEPTION("1","99999","未预知异常");
    private final String code;
    private final String errorCode;
    private final String errorDesc;

    CommonCode(String code, String errorCode, String errorDesc) {
        this.code = code;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String errorCode() {
        return errorCode;
    }

    @Override
    public String errorDesc() {
        return errorDesc;
    }
}
