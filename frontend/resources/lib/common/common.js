var api = "http://127.0.0.1:8080/";

//for ajax requirment, take token to backend
var token = $.cookie("TOKEN");

// //如果访问登陆页面这外的页面并且还没有登陆成功之后写入cookie的token就转到登陆页面
if (
    token == undefined &&
    window.location != "http://127.0.0.1:8000/login.html"
) {
    window.top.location = "http://127.0.0.1:8000/login.html";
}
