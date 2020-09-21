package com.zhongpeiqi.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongpeiqi.entity.Perm;
import com.zhongpeiqi.entity.Role;
import com.zhongpeiqi.entity.User;
import com.zhongpeiqi.service.PermService;
import com.zhongpeiqi.service.RoleService;
import com.zhongpeiqi.service.UserService;
import com.zhongpeiqi.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = roleService.getByUsername(user.getUsername());
        List<Perm> perms = permService.getByUsername(user.getUsername());
        if (CollectionUtils.isEmpty(roles) && CollectionUtils.isEmpty(perms)) {
            return null;
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles.stream().map(Role::getRoleName).collect(Collectors.toSet()));
        simpleAuthorizationInfo.addStringPermissions(perms.stream().map(Perm::getPermName).collect(Collectors.toSet()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        // 根据用户名查询密码，由安全管理器负责对比查询出的数据库中的密码和页面驶入的密码是否一致
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new AuthenticationException();
        }

        // 这里可以查询角色，无角色抛出异常
        String password = user.getPassword();
        // 单用户登录
        ShiroUtil.kickOutUser(username, true);
        // 最后的比对交给安全处理器，三个参数进行初步的简单认证信息对象的包装，由安全管理器包装运行
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}
