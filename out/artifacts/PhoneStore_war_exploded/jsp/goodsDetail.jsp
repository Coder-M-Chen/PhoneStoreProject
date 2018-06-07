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
<header id="fix">
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
                <div class="shopping-car"><a href="${pageContext.request.contextPath}/cart_findByUserId.action?userId=<s:property value="#session.userEntity.userId"/>"><span class="glyphicon glyphicon-shopping-cart" style="color: #3E82FF;margin-right: 6px;"></span>购物车</a></div>
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
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
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
        <div class="info">
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页 / </a></li>
                <li><a href=""><s:property value="#session.goodsEntity.goodsName"/></a></li>
            </ul>
        </div>
        <div class="details">
            <div class="pic">
                <div class="bigPic" id="large">
                    <img src="${pageContext.request.contextPath}/images/<s:property value="#session.goodsEntity.goodsId"/>.png" style="display: block;" data-zoom-image="${pageContext.request.contextPath}/images/<s:property value="#session.goodsEntity.goodsId"/>.png" class="zoom-01">
                </div>
            </div>
            <div class="texts" style="width: 624px;">
                <div class="mainInfo">
                    <h2 id="descript-info"><s:property value="#session.goodsEntity.goodsName"/></h2><br/>
                    <span></span>
                    <p class="descrip"><s:property value="#session.goodsEntity.goodsInfo"/></p>
                    <p class="price">¥<s:property value="#session.goodsEntity.goodsPrice"/></p>
                </div>
                <div class="commonInfo">
                    <dl>
                        <dt><span>颜色<em></em></span><i>:</i></dt>

                        <form id="myForm" method="post">
                            <dd>
                                <input type="radio" name="goodSet" value="暗夜灰" checked/>
                                <span>暗夜灰</span>
                            </dd>
                            <dd>
                                <input type="radio" name="goodSet" value="闪烁银"/>
                                <span>闪烁银</span>
                            </dd>
                        </form>
                    </dl>
                    <dl>
                        <dt><span>制式<em></em></span><i>:</i></dt>
                        <dd><span>全网通</span></dd>
                    </dl>
                    <dl>
                        <dt><span>版本<em></em></span><i>:</i></dt>
                        <dd><span>4GB+6GB</span></dd>
                    </dl>

                    <ul class="car">
                        <s:if test="#session.userEntity!=null">
                            <li>
                                <button onclick="fun1()" class="buy">加入购物车</button>
                            </li>
                            <li>
                                <button onclick="fun2()" class="buy">直接购买</button>
                            </li>
                            <script>
                                function fun1() {
                                    var goodsSetRadio=document.getElementsByName("goodSet");
                                    var goodsSet;
                                    for (var i = 0;i<goodsSetRadio.length;i++){
                                        if(goodsSetRadio[i].checked){
                                            goodsSet = goodsSetRadio[i].value;
                                        }
                                    }
                                    var myForm = document.getElementById("myForm");
                                    myForm.setAttribute('action','cart_add.action');
                                    myForm.submit();
                                    console.log(myForm.formData);
                                }
                                function fun2() {
                                    var goodsSetRadio=document.getElementsByName("goodSet");
                                    var goodsSet;
                                    for (var i = 0;i<goodsSetRadio.length;i++){
                                        if(goodsSetRadio[i].checked){
                                            goodsSet = goodsSetRadio[i].value;
                                        }
                                    }
                                    var myForm = document.getElementById("myForm");
                                    myForm.setAttribute('action','cart_addGoodAndPay.action');
                                    myForm.submit();
                                    console.log(myForm.formData);
                                }
                            </script>
                        </s:if>
                        <s:else>
                            <li><a href="${pageContext.request.contextPath}/jsp/login.jsp" class="buy">登录</a></li>
                        </s:else>
                    </ul>
                    <%--<ul class="car">--%>
                        <%--<s:if test="#session.userEntity!=null">--%>
                            <%--<li><a href="cart_add.action" class="buy">加入购物车</a></li>--%>
                            <%--<li><a href="cart_addGoodAndPay.action" class="buy">立即购买</a></li>--%>
                        <%--</s:if>--%>
                        <%--<s:else>--%>
                            <%--<li><a href="${pageContext.request.contextPath}/jsp/login.jsp" class="buy">登录</a></li>--%>
                        <%--</s:else>--%>
                    <%--</ul>--%>
                </div>
            </div>
        </div>
    </div>
    <div class="box">
            <div class="show">
                <div class="show-nav">
                    <ul>
                        <li><span style="color: #fc685d;">用户评论</span></li>
                    </ul>
                </div>
                <div class="content-box">
                    <div class="show-content"  style="display: block;">
                        <s:if test="#session.commentEntity!=null">

                            <div style="padding:30px 10px;background: #fff;">

                                <li class="gitment-comment" style="width: 1333px;padding-left: 10%">
                                    <a class="gitment-comment-avatar">
                                        <img class="gitment-comment-avatar-img" src="${pageContext.request.contextPath}/images/<s:property value="#session.commentEntity.userId"/>.png">
                                    </a>
                                    <div class="gitment-comment-main">
                                        <div class="gitment-comment-header">
                                            <a class="gitment-comment-name">
                                                <s:property value="#session.commentEntity.userId"/>
                                            </a>
                                                <%--<span>2018.05.17</span>--%>

                                            <div class="gitment-comment-like-btn">
                                                <svg class="gitment-heart-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50">
                                                    <path d="M25 39.7l-.6-.5C11.5 28.7 8 25 8 19c0-5 4-9 9-9 4.1 0 6.4 2.3 8 4.1 1.6-1.8 3.9-4.1 8-4.1 5 0 9 4 9 9 0 6-3.5 9.7-16.4 20.2l-.6.5zM17 12c-3.9 0-7 3.1-7 7 0 5.1 3.2 8.5 15 18.1 11.8-9.6 15-13 15-18.1 0-3.9-3.1-7-7-7-3.5 0-5.4 2.1-6.9 3.8L25 17.1l-1.1-1.3C22.4 14.1 20.5 12 17 12z"></path>
                                                </svg>
                                                <s:property value="#session.commentEntity.commentStar"/>
                                            </div>
                                        </div>
                                        <div class="gitment-comment-body gitment-markdown">
                                            <p>
                                                <s:property value="#session.commentEntity.commentInfo"/>
                                            </p>
                                        </div>
                                    </div>
                                </li>

                            </div>
                            <div style="width: 100%;margin: 10px auto"><a href="comment_findByGood.action" style="margin-left: 50%">查看全部评论</a></div>
                        </s:if>

                    </div>
                </div>
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