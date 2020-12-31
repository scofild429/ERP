var api = "http://127.0.0.1:8080/";
var loginUrl = "http://127.0.0.1:8000/login.html";

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
