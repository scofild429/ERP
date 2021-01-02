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

    public static final ResultObj DELETE_SUCCESS = new ResultObj(200, "delete success");
    public static final ResultObj DELETE_ERROR = new ResultObj(-1, "delete failed");

    public static final ResultObj ADD_SUCCESS = new ResultObj(200, "add success");
    public static final ResultObj ADD_ERROR = new ResultObj(-1, "add failed");

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(200, "update success");
    public static final ResultObj UPDATE_ERROR = new ResultObj(-1, "update failed");

    private  Integer code = 200;
    private  String msg = "";
    private  String token = "";


    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
