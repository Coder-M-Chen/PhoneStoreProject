<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WHUT Phone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script src="${pageContext.request.contextPath}/lib/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/jquery.cookie.js"></script>
</head>
<body id="body1">
<header>
    <div class="nav-box">
        <div class="nav">
            <ul class="nav-left">
                <li><a href="#">WHUT Phone</a></li>
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
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="goods_findAllUser.action?currPage=1">所有商品</a></li>
            </ul>

            <form action="goods_findByGoodsName.action" method="post">
                <input type="text" name="goodsName" id="search" value="Mi">
                <a href="goods_findByGoodsName.action" class="search-icon">
                    <span class="glyphicon glyphicon-search"></span>
                </a>
            </form>
        </div>
    </div>

    <div class="banner">
        <div id="container">

            <ul>
                <s:iterator value="#session.newsTopFiveList" var="d">
                    <li>
                        <a href="news_toNewsList.action">
                            <img src="${pageContext.request.contextPath}/images/<s:property value="#d.newsId"/>.png" alt="">
                        </a>
                    </li>
                </s:iterator>
            </ul>

            <ol>
                <s:iterator value="#session.newsTopFiveList" var="d" status="index">
                    <s:if test="#index.index==0">
                        <li class="light"></li>
                    </s:if>
                    <s:else>
                        <li></li>
                    </s:else>
                    <%--<li></li>--%>
                </s:iterator>
            </ol>
        </div>
        <script>
            $(function(){
                var uli = $("#container ul li"),
                    imgs = $("#container ul li img"),
                    oli = $("#container ol li"),
                    imgWidth = imgs.width(),
                    ul = $("#container ul").eq(0),
                    len = imgs.length,
                    ulWidth = imgWidth * len,
                    nowi = 0,
                    circleIndex;
                ul.css("width",ulWidth);
                // console.log(imgWidth,ulWidth,ul);
                //使图片居中显示
                function toResize(){
                    var viewWidth = document.documentElement.clientWidth;
                    if(viewWidth>1000){
                        offX = -(imgWidth - viewWidth)/2;
                        imgs.each(function(){
                            $(this).css("left",offX);
                        });
                    }
                }
                toResize();
                $(window).on("resize",toResize);
                //运动函数
                function move(){
                    nowi++;
                    circleIndex = nowi;
                    if(nowi == len - 1){
                        circleIndex = 0;
                    }
                    oli.eq(circleIndex).addClass("light").siblings().removeClass("light");
                    var _left = -nowi * imgWidth;
                    ul.animate({"left":_left},"normal",function(){
                        if(nowi == len - 1){
                            ul.css("left",0);
                            nowi = 0;
                        }
                    });
                }
                //添加定时器,实现自动轮播
                var timer = setInterval(move,3000);
                //给小圆点添加点击事件
                $("#container ol").on("click","li",function(){
                    var index = $(this).index();
                    nowi = index - 1;
                    move();
                });
                //给整个容器添加鼠标移入移出让轮播停止和继续的事件
                $("#container").on("mouseenter",function(){
                    clearInterval(timer);
                });
                $("#container").on("mouseleave",function(){
                    timer = setInterval(move,3000);
                });
            });
        </script>
    </div>

    <div class="main-body" style="width: 80%;margin: 0 auto;min-width:1256px">
        <div class="mobileZone" >
            <h2>所有商品</h2>

            <dl>
                <div clASS="right_box" style="float: left;">
                    <s:iterator value="list" var="d" status="index">
                        <s:if test="#index.index%4==0">
                            <div class="right_top">
                        </s:if>
                        <dd>
                            <a href="goods_findById.action?goodId=<s:property value="#d.goodsId"/>">
                                <img src="${pageContext.request.contextPath}/images/<s:property value="#d.goodsId"/>.png" style="height: 140px;">
                                <span><s:property value="#d.goodsName"/></span>
                                <em class="price">
                                    ¥ &nbsp;<s:property value="#d.goodsPrice"/>
                                </em>
                            </a>
                        </dd>
                        <s:if test="(#index.index+1)%4==0||#index.last">
                            </div>
                        </s:if>
                    </s:iterator>
                </div>
            </dl>
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