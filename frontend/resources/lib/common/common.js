var api = "http://127.0.0.1:8080/";
var loginUrl = "http://127.0.0.1:8000/login.html";

var baseUrl = "http://localhost/";
//for ajax requirment, take token to backend
var token = $.cookie("TOKEN");

$.ajaxSetup({
    headers: {
        TOKEN: token,
    },
});

//如果访问登陆页面这外的页面并且还没有登陆成功之后写入cookie的token就转到登陆页面
if (token == undefined) {
    if (window.location != loginUrl) {
        window.top.location = loginUrl;
    }
} else {
    if (window.location != loginUrl) {
        $.ajax({
            url: api + "login/checkLogin",
            async: true,
            type: "post",
            dataType: "json",
            success: function(res) {
                if (res.code == -1) {
                    window.top.location = loginUrl;
                }
            },
            error: function(res) {
                window.top.location = loginUrl;
            },
        });
    }
}

var pers = localStorage.getItem("permissions");
var usertype = localStorage.getItem("usertype");

if (usertype == 1) {
    if (pers != null) {
        var permissions = pers.split(",");
        //部门权限开始
        if (permissions.indexOf("dept:add") < 0) {
            $(".btn_add").hide();
        }
        if (permissions.indexOf("dept:update") < 0) {
            $(".btn_update").hide();
        }
        if (permissions.indexOf("dept:delete") < 0) {
            $(".btn_delete").hide();
        }
        //部门权限结束
    } else {
        $(".btn_add").hide();
        $(".btn_update").hide();
        $(".btn_delete").hide();
        $(".btn_dispatch").hide();
        $(".btn_reset").hide();
    }
}

//给页面显示登陆用户名
var username = localStorage.getItem("username");
$(".login_name").html(username);
