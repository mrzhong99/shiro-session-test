package com.zhongpeiqi.filter;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zhongpeiqi.common.code.CommonCode;
import com.zhongpeiqi.common.response.ResponseResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter writer = httpServletResponse.getWriter();
        ResponseResult fail = ResponseResult.FAIL(CommonCode.NOT_LOGIN);
        JSONObject jsonObject = JSONUtil.parseObj(fail);
        writer.print(jsonObject);
        writer.flush();
        writer.close();
        return false;
    }
}
