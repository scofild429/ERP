package com.mypro.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    private  Integer code = 200;
    private  String msg = "";
    private  String token = "";


    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
