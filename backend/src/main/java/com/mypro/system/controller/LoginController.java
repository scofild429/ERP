package com.mypro.system.controller;


import com.mypro.system.common.ActiveUser;
import com.mypro.system.common.Constant;
import com.mypro.system.common.MenuTreeNode;
import com.mypro.system.common.ResultObj;
import com.mypro.system.domain.Loginfo;
import com.mypro.system.domain.Menu;
import com.mypro.system.domain.User;
import com.mypro.system.service.LoginfoService;
import com.mypro.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-04 09:33
 **/


//@CrossOrigin
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private LoginfoService loginfoService;

    /**
     * 用户登陆
     */
    @RequestMapping("doLogin")
    @ResponseBody
    public ResultObj doLogin(String loginname, String  password, HttpServletRequest request){
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(loginname, password);
            subject.login(loginToken);
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            // get the sessionid of shiro, naming token
            String token = subject.getSession().getId().toString();
            //login daily
            User user = activeUser.getUser();
            Loginfo loginfo = new Loginfo();
            loginfo.setLoginname(user.getName()+"-"+user.getLoginname());
            loginfo.setLoginip(request.getRemoteAddr());
            loginfo.setLogintime(new Date());
            loginfoService.save(loginfo);

            List<String> permissions = activeUser.getPermissions();
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("permissions", permissions);
            map.put("usertype",user.getType());
            map.put("username", user.getName());
            return new ResultObj(200, "success login", map);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return  new ResultObj(-1,"username or password is wrong");
        }
    }

    /**
     * 加载所有菜单【顶部菜单和左侧菜单】
     */
    @RequestMapping("loadIndexMenu")
    @ResponseBody
    public Object loadIndexMenu(){
        //得到当前登陆的用户
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        if(null==activeUser){
            return null;
        }
        User user=activeUser.getUser();
        List<Menu> menus=null;
        if(user.getType().equals(Constant.USER_TYPE_SUPER)){//超级管理员
            menus=menuService.queryAllMenuForList();
        }else{
            menus=menuService.queryMenuForListByUserId(user.getId());
        }

        List<MenuTreeNode> treeNodes=new ArrayList<>();

        for (Menu m : menus) {
            Boolean spread=m.getSpread()==Constant.SPREAD_TRUE?true:false;
            treeNodes.add(new MenuTreeNode(m.getId(),m.getPid(),m.getTitle(),m.getHref(),m.getIcon(),spread,m.getTarget(),m.getTypeCode()));
        }
        List<MenuTreeNode> nodes = MenuTreeNode.MenuTreeNodeBuilder.build(treeNodes, 0);
        Map<String,Object> res=new HashMap<>();
        for (MenuTreeNode n : nodes) {
            res.put(n.getTypecode(),n);
        }
        return res;
    }


    /**
     * 验证当前token是否登陆
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public ResultObj checkLogin(){
        Subject subject=SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return ResultObj.IS_LOGIN;
        }else{
            return ResultObj.UN_LOGIN;
        }
    }

//
//    /**
//     * 验证码  打
//     *
//     * redis   key value
//     */
//    @RequestMapping("captcha")
//    public void captcha(HttpServletResponse response,String codeKey) throws IOException {
//        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 38, 4, 4);
//        String code = captcha.getCode();
//        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
//        opsForValue.set(codeKey,code);
//        opsForValue.getOperations().expire(codeKey,60, TimeUnit.SECONDS);
//        captcha.write(response.getOutputStream());
//    }
//




}
