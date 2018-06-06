<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>WHUT Phone</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
    <script src="${pageContext.request.contextPath}/lib/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/template-web.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/jquery.cookie.js"></script>
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
<div id="main">
    <div class="header">
        <h1 class="logo"><a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/zx-logo.png" alt="#"></a></h1>
        <div class="right">
            <ul style="float:left;">
                <li><a href="#">首页</a></li>
                <li><a href="goods_findAllUser.action">所有商品</a></li>
            </ul>

            <form action="goods_findByGoodsName.action" method="post">
                <input type="text" name="goodsName" id="search" value="Mi">
                <a href="goods_findByGoodsName.action" class="search-icon">
                    <span class="glyphicon glyphicon-search"></span>
                </a>
            </form>
        </div>
    </div>
    <div class="content">

        <div class="moban2">
            <s:iterator value="list" var="d">
                <div class="template2" style="display: block;text-align: center;margin-left: 10%;margin-right: 10%">
                    <ul>
                        <li>
                            <%--<a href="news_findById.action?currNewsId=<s:property value="#d.newsId"/>">--%>
                                <img src="${pageContext.request.contextPath}/images/<s:property value="#d.newsId"/>.png" alt="">
                            <%--</a>--%>
                        </li>
                        <li style="width: 70%">
                            <%--<a href="news_findById.action?currNewsId=<s:property value="#d.newsId"/>">--%>
                                <h2 class="details2"><s:property value="#d.newsHeader" escapeHtml="false"/></h2>
                            <%--</a>--%>
                            <p class="description2"><s:property value="#d.newsBody" escapeHtml="false"/></p>
                        </li>
                    </ul>
                </div>
            </s:iterator>
        </div>

    </div>
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