package com.mypro.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-04 17:38
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class RoleVo extends  BaseVo{

    private Integer userId;

    private String name;
    private String remark;

}
