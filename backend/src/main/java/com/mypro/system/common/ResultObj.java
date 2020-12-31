package com.mypro.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    public static final ResultObj IS_LOGIN = new ResultObj(200, "login successful");
    public static final ResultObj UN_LOGIN = new ResultObj(-1, "login failed");
    private  Integer code = 200;
    private  String msg = "";
    private  String token = "";


    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
