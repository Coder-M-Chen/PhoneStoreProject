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
<script type="text/javascript">
    window.location = 'type_initJsp.action';
</script>
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
            <div class="shopping-car"><a href="${pageContext.request.contextPath}/jsp/cart.jsp"><span class="glyphicon glyphicon-shopping-cart" style="color: #3E82FF;margin-right: 6px;"></span>购物车</a></div>
        </div>
    </div>
</header>
<div id="main">
    <div class="header">
        <h1 class="logo"><a href="#"><img src="${pageContext.request.contextPath}/images/zx-logo.png" alt="#"></a></h1>
        <div class="right">
            <ul style="float:left;">
                <li><a href="#">首页</a></li>
                <li><a href="goods_findAll.action">所有商品</a></li>
            </ul>
            <form action="#" >
                <input type="text" value="天机7" id="search">
                <a href="#" class="search-icon"><span class="glyphicon glyphicon-search"></span></a>
            </form>
        </div>
    </div>
    <div class="banner">
        <div id="container">
            <ul>
                <!-- <li><a href="#"><img src="images/6.jpg" alt=""></a></li> -->
                <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/images/1.jpg" alt=""></a></li>
                <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/images/2.jpg" alt=""></a></li>
                <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/images/3.jpg" alt=""></a></li>
                <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/images/4.jpg" alt=""></a></li>
                <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/images/5.jpg" alt=""></a></li>
                <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/images/6.jpg" alt=""></a></li>
                <li><a href="javascript:void(0);"><img src="${pageContext.request.contextPath}/images/1.jpg" alt=""></a></li>
            </ul>
            <ol>
                <li class="light"></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
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
                                    <img src="${pageContext.request.contextPath}/images/img4.jpg" alt="">
                                    <span><s:property value="#d.goodsName"/></span>
                                    <em class="price">¥<s:property value="d.goodsPrice"/></em>
                                </a>
                            </dd>

                        <s:if test="(#index.index+1)%4==0||#index.last">
                            </div>
                        </s:if>

                    </s:iterator>
                    <div class="right_top">
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img4.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img5.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img6.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                    </div>
                    <div class="right_bottom">
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img7.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img7.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img8.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img9.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                    </div>
                    <div class="right_top">
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img4.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img4.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img5.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img6.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                    </div>
                    <div class="right_bottom">
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img7.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img7.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img8.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                        <dd><a href="#"><img src="${pageContext.request.contextPath}/images/img9.jpg" alt=""><span>ZTE中兴 天机A2017(全网通)</span><em class="price">¥3299.0</em></a></dd>
                    </div>
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
    <div class="ft-middle">
        <dl>
            <dt><a href="#">关注我们</a></dt>
            <dd><a href="#">中兴社区</a></dd>
            <dd><a href="#">官方微信</a></dd>
            <dd><a href="#">新浪微博</a></dd>
            <dd><a href="#"></a></dd>
        </dl>
        <dl>
            <dt><a href="#">帮助中心</a></dt>
            <dd><a href="#">支付方式</a></dd>
            <dd><a href="#">购物指南</a></dd>
            <dd><a href="#">配送指南</a></dd>
            <dd><a href="#"></a></dd>
        </dl>
        <dl>
            <dt><a href="#">售后服务</a></dt>
            <dd><a href="#">退换货流程</a></dd>
            <dd><a href="#">售后网点</a></dd>
            <dd><a href="#">退换货政策</a></dd>
            <dd><a href="#">保修政策</a></dd>
        </dl>
        <dl>
            <dt><a href="#">关于商城</a></dt>
            <dd><a href="#">联系我们</a></dd>
            <dd><a href="#">商城简介</a></dd>
            <dd><a href="#">礼品团购</a></dd>
            <dd><a href="#"></a></dd>
        </dl>
        <div class="contactInfo">
            <p>400-800-9999</p>
            <span>周一到周日 8:30-20:30 (全面无休)</span>
        </div>
    </div>
    <div class="ft-bottom">
        <p class="ft-nav">
            <a href="#">中兴社区 |</a>
            <a href="#">中兴通讯 |</a>
            <a href="#">汇天地 |</a>
            <a href="#">中兴云服务|</a>
            <a href="#">中兴手机</a>
        </p>
        <p><a href="#">服务协议 </a>| ©2014 - 2017  深圳市中兴智谷科技有限公司版权所有，并保留所有权利
        </p>
        <p>增值电信业务经营许可证：粤B2-20160352 | ICP备案证书号 : <a href="#">粤ICP备16018970号 </a></p>
    </div>
</div>
</body>
</html>