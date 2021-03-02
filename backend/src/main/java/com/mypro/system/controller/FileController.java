package com.mypro.system.controller;

import com.mypro.system.common.ActiveUser;
import com.mypro.system.common.DataGridView;
import com.mypro.system.common.upload.UploadService;
import com.mypro.system.domain.User;
import com.mypro.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-10 17:29
 **/

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UserService userService;

    /**
     * 上传文件
     */
    @RequestMapping("uploadFile")
    public Object uploadFile(MultipartFile mf){
        String path = this.uploadService.uploadImage(mf);
        Map<String,String> map=new HashMap<>();
        map.put("src",path);
        //更新数据库
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        User user = activeUser.getUser();
        user.setImgpath(path);
        userService.updateUser(user);
        return new DataGridView(map);
    }


    /**
     * 上传文件
     */
    @RequestMapping("uploadGoodsFile")
    public Object uploadGoodsFile(MultipartFile mf){

        String path = this.uploadService.uploadImage(mf);

        Map<String,String> map=new HashMap<>();
        map.put("src",path);
        return new DataGridView(map);
    }
}
