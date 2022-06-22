<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆页面</title>
</head>
<body>
<div class="login">
    <h1>孤独的旅行家</h1>
    <form class="layui-form" action="${BasePath}/user/login" method="post">
        <div class="layui-form-item">
            <input class="layui-input" name="loginName" value="" placeholder="请输入用户名" lay-verify="required" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="password" value="" placeholder="请输入密码" lay-verify="required" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
            <div class="code"><img src="${BasePath}/user/login/code" width="116" height="36" id="mycode"></div>
        </div>
        <div class="layui-form-item">
            <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked title="记住帐号?">
        </div>
        <div class="layui-form-item">
            <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
        </div>
        <div class="layui-form-item">
            <fieldset class="layui-elem-field">
                <div class="layui-field-box" style="color: #fff;font-size: 20px;">
                    用户名:test &nbsp;&nbsp;&nbsp;密码:1
                </div>
            </fieldset>
        </div>
    </form>
</div>
</body>
</html>