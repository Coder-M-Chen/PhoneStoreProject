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

    <p>出错原因：<s:property value="#session.errorInfo"/></p>
    <br>
    <p>请联系相关技术人员</p>
    <s:if test="#session.userEntity==null">
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">重新登录</a>
    </s:if>
    <s:elseif test="#session.userEntity.userType=='A'.toString()">
        <a href="${pageContext.request.contextPath}/jsp/personal.jsp">返回个人中心</a>
    </s:elseif>
    <s:else>
        <a href="${pageContext.request.contextPath}/index.jsp">返回主页</a>
    </s:else>

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
</body>
</html>