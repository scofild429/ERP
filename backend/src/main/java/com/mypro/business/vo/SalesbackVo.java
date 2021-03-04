package com.mypro.business.vo;

import com.mypro.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *@ClassName InportVo
 *@Description TODO
 *@Auther Silin
 *@Date 01.03.21 16:38
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesbackVo extends BaseVo {

    private Integer customerid;
    private Integer goodsid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;



}
