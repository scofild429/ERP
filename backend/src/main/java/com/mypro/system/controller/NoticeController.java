package com.mypro.system.controller;


import com.mypro.system.common.ActiveUser;
import com.mypro.system.common.ResultObj;
import com.mypro.system.domain.Notice;
import com.mypro.system.service.NoticeService;
import com.mypro.system.vo.NoticeVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("notice")
@RestController
public class NoticeController {
/**
 *@ClassName NoticeController
 *@Description TODO
 *@Auther Silin
 *@Date 02.01.21 14:02
 **/

    @Autowired
    private NoticeService noticeService;


    /**
     * query
     */

    @RequestMapping("loadAllNotice")
    public Object loadAllNotice(NoticeVo noticeVo){
        return this.noticeService.queryAllNotice(noticeVo);
    }


    /**
     * add
     */
    @RequestMapping("addNotice")
    public ResultObj addNotice(Notice notice){
        try {
            notice.setCreatetime(new Date());
            Subject subject = SecurityUtils.getSubject();
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            notice.setOpername(activeUser.getUser().getName());
            this.noticeService.save(notice);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * update
     */
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(Notice notice){
        try {
            this.noticeService.updateById(notice);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * delete
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * batchdelete
     */
    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteLoginfo(Integer[] ids){
        try {
            if (null!=ids&&ids.length>0) {
                List<Integer> idsList = new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.noticeService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            }else {
                return new ResultObj(-1,"the passed ID can't be empty");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }






}
