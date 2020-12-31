package com.mypro.system.vo;

import com.mypro.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-04 17:38
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class MenuVo extends BaseVo {
    Integer hasPermission;  //0不要权限
}
