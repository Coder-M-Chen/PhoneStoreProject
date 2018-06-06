<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/13
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <script src="${pageContext.request.contextPath}/lib/jquery-1.12.4.min.js"></script>
</head>
<body id="body1">
<header>
    <div class="nav-box">
        <div class="nav">
            <ul class="nav-left">
                <li><a href="${pageContext.request.contextPath}/index.jsp">WHUT Phone</a></li>
            </ul>
            <ul class="nav-right">
                <s:if test="#session.userEntity!=null">
                    <li><a href="${pageContext.request.contextPath}/jsp/personal.jsp"><s:property value="#session.userEntity.vName"/></a> </li>
                    <li><a href="${pageContext.request.contextPath}/user_logOff.action">注销</a> </li>
                </s:if>
                <s:if test="#session.userEntity==null">
                    <li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
                </s:if>
            </ul>
            <s:if test="#session.userEntity!=null">
                <div class="shopping-car"><a href="${pageContext.request.contextPath}/cart_findByUserId.action"><span class="glyphicon glyphicon-shopping-cart" style="color: #3E82FF;margin-right: 6px;"></span>购物车</a></div>
            </s:if>
            <s:else>
                <div class="shopping-car"><a href="${pageContext.request.contextPath}/jsp/login.jsp"><span class="glyphicon glyphicon-shopping-cart" style="color: #3E82FF;margin-right: 6px;"></span>购物车</a></div>
            </s:else>
        </div>
    </div>
</header>
<div class="content">
    <div class="pic"><a href="#"><img src="${pageContext.request.contextPath}/images/login1.jpg" alt=""></a></div>
    <h2>欢迎注册</h2>
    <form action="user_register.action" name="formRegister" method="post" id="form_user_register">
        <ul>
            <li><input type="text" placeholder="请输入手机号" id="username" name="userId"></li>
            <li><input type="password" placeholder="输入6位及以上数字字母" id="passwords" name="password"></li>
            <li><input type="password" placeholder="再次输入登录密码" disabled  id="again"></li>
            <li><input type="text" placeholder="验证码" id="authCode"><span id="codebox"></span></li>
        </ul>
        <span class="change">更换验证码</span>
        <button id="login">立即注册</button>
        <p class="register"><a href="/jsp/login.jsp" style="color: #0076b9;">已有账号，立即登录</a></p>
    </form>
</div>
<div id="footer">
    <div class="ft-top">
        <ul>
            <li>7天退货保障</li>
            <li>15天换货承诺</li>
            <li>150元起全场免运费</li>
            <li>全国售后服务点</li>
        </ul>
    </div>
</div>
<script>
    $(function(){
        //页面生成时产生一个随机验证码;
        function yanzhengma(){
            var code = gencode();
            $("#codebox").text(code);
        }
        yanzhengma();
        //点击更换验证码的时候重新生成一个验证码
        $(".change").eq(0).click(function(){
            yanzhengma();
        });
        //生成验证码的函数
        function gencode(length){
            if(typeof length === "undefined"){
                length = 4;
            }
            var rand,count,code="";
            count = 0;
            while(count < length){
                rand = (Math.floor(Math.random()*(122-48+1))+48);
                if(rand>57 && rand<65 || rand>90 && rand<97){
                    continue;
                }
                code += String.fromCharCode(rand);
                count++;
            }
            return code;
        }

        //验证用户名格式是否输入正确
        $("#username").on("blur",function(){
            var reg = /^1[34578]\d{9}$/;
            var result = reg.test($("#username").val());
            if(!result){
                var content = "<div>请输入正确的手机号格式</div>";
                $(content).insertAfter("#username");
            }
        });
        // 给密码框进行正则表达式验证
        $("#passwords").on("blur",function(){
            var pass = $("#passwords").val();
            //^[a-zA-Z]\w{5,17}$(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
            var reg = /[a-zA-Z0-9]{6,}/;//6位以上的数字或字母
            var result = reg.test(pass);
            if(!result){
                // console.log("55555");
                var html = "<div>密码输入不正确<div/>";
                $(html).insertAfter("#passwords");
                // $("#again").attr("disabled");
            }else{
                $("#again").removeAttr("disabled");
            }
        });
        //两个密码框及名字框及验证码框再次输入获的焦点时清楚之前产生的兄弟节点
        $("#username").on("focus",function(){
            $(this).siblings().remove();
        });
        $("#passwords").on("focus",function(){
            $(this).siblings().remove();
        });
        $("#again").on("focus",function(){
            $(this).siblings().remove();
        });
        $("#authCode").on("focus",function(){
            $(this).siblings().remove("div");
        });
        //密码确认时看两次是否一致
        $("#again").on("blur",function(){
            var pass = $("#passwords").val();
            var again = $("#again").val();
            // console.log(pass);
            // console.log(again);
            if(pass !== again){
                var html = "<div>两次密码不一致,请重新输入</div>";
                $(html).insertAfter("#again");
            }
        });
        //验证码的输入是否一致
        $("#authCode").on("blur",function(){
            var authCode = $("#authCode").val();
            var code = $("#codebox").text();
            if(authCode.toUpperCase() !== code.toUpperCase()){
                // console.log("验证码输入不一致");
                var html = "<div>验证码输入不一致</div>";
                $(html).insertAfter("#codebox");
            }else{//验证码输入一致后点击才会有效
                var form = document.getElementById('form_user_register');
                form.onsubmit;
            }
        });
    });
</script>
</body>
</html>