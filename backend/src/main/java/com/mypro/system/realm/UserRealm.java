package com.mypro.system.realm;


import com.mypro.system.common.ActiveUser;
import com.mypro.system.domain.User;
import com.mypro.system.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: erp
 * @author: 雷哥
 * @create: 2020年1月4日9:49:35
 **/

public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    private MenuService menuService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName=token.getPrincipal().toString();
        User user = this.userService.queryUserByLoginName(userName);
        if(null!=user){
            ActiveUser activeUser=new ActiveUser();
            activeUser.setUser(user);

//            //根据用户ID查询角色名称的集合
//            List<String> roles=this.roleService.queryRoleNamesByUserId(user.getId());
//            //根据用户ID去查询权限编码的集合
//            List<String> permissions=this.menuService.queryPermissionCodesByUserId(user.getId());
//            activeUser.setRoles(roles);
//            activeUser.setPermissions(permissions);
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(activeUser,user.getPwd(), ByteSource.Util.bytes(user.getSalt()),getName());
            return info;
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SimpleAuthorizationInfo  info =new SimpleAuthorizationInfo();
//        ActiveUser activeUser= (ActiveUser) principals.getPrimaryPrincipal();
//        List<String> permissions = activeUser.getPermissions();
//        List<String> roles = activeUser.getRoles();
//        User user = activeUser.getUser();
//        if(user.getType().equals(Constant.USER_TYPE_SUPER)){
//            info.addStringPermission("*:*");
//        }else{
//            if(null!=roles&&roles.size()>0){
//                info.addRoles(roles);
//            }
//            if(null!=permissions&&permissions.size()>0){
//                info.addStringPermissions(permissions);
//            }
//        }
//        return info;
//    }
}
