<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
    <script src="${pageContext.request.contextPath}/lib/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/jquery.elevateZoom-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/jquery.fly.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/detail.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pinglun.css">
    <style>
        .test{
            height: auto;
            margin: 0 15%;
        }
    </style>
</head>
<body id="body1">
<div class="header">
    <h1 class="logo"><a href="#"><img src="${pageContext.request.contextPath}/images/zx-logo.png" alt="#"></a></h1>
    <div class="right">
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
            <li><a href="goods_findAllUser.action?currPage=1">所有商品</a></li>
            <%--商品类型--%>
        </ul>
        <form action="goods_findByGoodsName.action" method="post" id="searchBoxForm">
            <input type="text" name="goodsName" id="search" placeholder="商品名称">
            <a href="goods_findByGoodsName.action" class="search-icon">
                <span class="glyphicon glyphicon-search"></span>
            </a>
        </form>
    </div>
</div>
<div id="main">
    <div class="header">
        <h1 class="logo"><a href="#"><img src="${pageContext.request.contextPath}/images/zx-logo.png" alt="#"></a></h1>
        <div class="right">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="#">所有商品</a></li>
            </ul>
            <form action="goods_findByGoodsName.action" method="post" id="searchBoxForm">
                <input type="text" name="goodsName" id="search" placeholder="商品名称">
                <a href="goods_findByGoodsName.action" class="search-icon">
                    <span class="glyphicon glyphicon-search"></span>
                </a>
            </form>
        </div>
    </div>
    <div class="content">
        <div class="info">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页 / </a></li>
                <li><a href="goods_findByType.action?typeId=<s:property value="#session.goodsEntity.goodsType"/>"><s:property value="#session.typeEntity.typeName"/> / </a></li>
                <li><a href="#"><s:property value="#session.goodsEntity.goodsName"/></a></li>
            </ul>
        </div>
        <div class="details">
            <div class="pic">
                <div class="bigPic" id="large">
                    <%--<img src="${pageContext.request.contextPath}/images/datails (10).jpg" alt="" style="display: block;" data-zoom-image="${pageContext.request.contextPath}/images/datails (10).jpg" class="zoom-01">--%>
                        <img src="${pageContext.request.contextPath}/images/<s:property value="#session.goodsEntity.goodsId"/>.png" style="display: block;" data-zoom-image="${pageContext.request.contextPath}/images/<s:property value="#session.goodsEntity.goodsId"/>.png" class="zoom-01">
                </div>
            </div>
            <div class="texts">
                <div class="mainInfo">
                    <h2 id="descript-info"><s:property value="#session.goodsEntity.goodsName"/></h2><br/>
                    <span></span>
                    <p class="descrip"><s:property value="#session.goodsEntity.goodsInfo"/></p>
                    <p class="price">¥<s:property value="#session.goodsEntity.goodsPrice"/></p>
                </div>
                <div class="commonInfo">

                    <dl>
                        <%--<dt><span>颜色<em></em></span><i>:</i></dt>--%>

                        <%--<dd>--%>
                        <%--<input type="radio" name="goodSet" value="暗夜灰" checked/>--%>
                        <%--<span>暗夜灰</span>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                        <%--<input type="radio" name="goodSet" value="闪烁银"/>--%>
                        <%--<span>闪烁银</span>--%>
                        <%--</dd>--%>
                        <dt><span>版本<em></em></span><i>:</i></dt>
                        <dd><span>官方标配版</span></dd>
                    </dl>
                    <ul class="car">
                        <s:if test="#session.userEntity!=null">
                            <li><a href="cart_add.action" class="buy">加入购物车</a></li>
                            <%--未实现--%>
                            <li><a href="cart_addGoodAndPay.action" class="buy">立即购买</a></li>
                        </s:if>
                        <s:else>
                            <li><a href="${pageContext.request.contextPath}/jsp/login.jsp" class="buy">登录</a></li>
                        </s:else>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="test">
    <div lang="en-US" class="gitment-container gitment-comments-container">
        <ul class="gitment-comments-list">
            <s:iterator value="list" var="d">

                <li class="gitment-comment">
                    <a class="gitment-comment-avatar">
                        <img class="gitment-comment-avatar-img" src="../images/default-avatar.png">
                    </a>
                    <div class="gitment-comment-main">
                        <div class="gitment-comment-header">
                            <a class="gitment-comment-name">
                                <s:property value="#d.userId"/>
                            </a>
                            <span></span>

                            <div class="gitment-comment-like-btn">
                                <svg class="gitment-heart-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50">
                                    <path d="M25 39.7l-.6-.5C11.5 28.7 8 25 8 19c0-5 4-9 9-9 4.1 0 6.4 2.3 8 4.1 1.6-1.8 3.9-4.1 8-4.1 5 0 9 4 9 9 0 6-3.5 9.7-16.4 20.2l-.6.5zM17 12c-3.9 0-7 3.1-7 7 0 5.1 3.2 8.5 15 18.1 11.8-9.6 15-13 15-18.1 0-3.9-3.1-7-7-7-3.5 0-5.4 2.1-6.9 3.8L25 17.1l-1.1-1.3C22.4 14.1 20.5 12 17 12z"></path>
                                </svg>
                                <s:property value="#d.commentStar"/>
                            </div>
                        </div>
                        <div class="gitment-comment-body gitment-markdown"><p><s:property value="#d.commentInfo"/></p></div>
                    </div>
                </li>

            </s:iterator>

        </ul>
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
