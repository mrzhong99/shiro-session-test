package com.zhongpeiqi.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zhongpeiqi.common.code.CommonCode;
import com.zhongpeiqi.common.response.ResponseResult;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * roles：正常情况下URL路径的拦截设置如下:
 * /admins/user/**=roles[admin]
 * 参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如/admins/user/**=roles[“admin,guest”]
 * 但是这个设置方法是需要每个参数满足才算通过，相当于hasAllRoles()方法。也就是我们的角色必须同时拥有admin和guest权限才可以。
 *
 * 我们可以看到其实这个roles的filter是通过subject.hasAllRoles(roles)判断是否满足所有权限,但是我们真实项目中,很多时候用户只要满足其中一个角色即可认为是授权认证成功。
 * apache shiro 的角色过滤是 and的关系，需要重新写成or的关系。
 */
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;
        // 如果有角色限制，只满足一个角色即视为授权通过
        if (rolesArray != null && rolesArray.length > 0) {
            for (String s : rolesArray) {
                return subject.hasRole(s);
            }
        }
        return true;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        // 处理跨域问题，跨域请求首先会发一个options类型的预检请求,直接放行
        if (HttpMethod.OPTIONS.name().equals(httpRequest.getMethod())) {
            return true;
        }
        // 如果通过上面定义的isAccessAllowed，也直接放行
        boolean isAccess = isAccessAllowed(request, response, mappedValue);
        if (isAccess) {
            return true;
        }
        httpResponse.setCharacterEncoding("UTF-8");
        Subject subject = getSubject(request, response);
        PrintWriter writer = httpResponse.getWriter();
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        String respStr;
        if (subject.getPrincipal() == null) {
            respStr = new ObjectMapper().writeValueAsString(ResponseResult.FAIL(CommonCode.NOT_LOGIN));
        } else {
            respStr = new ObjectMapper().writeValueAsString(ResponseResult.FAIL(CommonCode.UNAUTHORIZED));
        }
        writer.print(respStr);
        writer.flush();
        httpResponse.setHeader("content-length", respStr.getBytes().length + "");
        return false;
    }
}
