package com.zhongpeiqi.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.zhongpeiqi.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Collection;
import java.util.Objects;


public class ShiroUtil {
    private static final RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    // 外界无法实例化该类
    private ShiroUtil() {
    }

    /**
     * 获取指定用户名的session
     * @param username
     * @return
     */
    private static Session getSessionByUsername(String username) {
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        User user;
        Object attribute;
        for (Session session : sessions) {
            attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }
            user = (User) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (user == null) {
                continue;
            }
            if (Objects.equals(user.getUsername(), username)) {
                return session;
            }
        }
        return null;
    }

    public static void kickOutUser(String username, boolean isRemoveSession) {
        Session session = getSessionByUsername(username);
        if (session == null) {
            return;
        }

        Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (attribute == null) {
            return;
        }

        User user = (User) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
        if (!username.equals(user.getUsername())) {
            return;
        }

        // 删除session
        if (isRemoveSession) {
            redisSessionDAO.delete(session);
        }
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        // 删除cache, 在访问受限接口时会重新授权
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
    }
}
