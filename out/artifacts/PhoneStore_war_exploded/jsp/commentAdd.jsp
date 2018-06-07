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
                    <img src="${pageContext.request.contextPath}/images/datails (10).jpg" alt="" style="display: block;" data-zoom-image="${pageContext.request.contextPath}/images/datails (10).jpg" class="zoom-01">
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
                        <dt><span>颜色<em></em></span><i>:</i></dt>
                        <dd><span>暗夜灰</span><span>闪烁银</span></dd>
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
    <div class="box">
        <div class="show" style="width: 100%">
            <div class="show-nav">
                <ul>
                    <li><span style="color: #fc685d;">用户评论</span></li>
                </ul>
            </div>
            <div class="content-box">



                <form action="comment_add.action" method="post">
                    <div lang="en-US" class="gitment-container gitment-editor-container">
                        <div class="gitment-editor-main">
                            <div class="gitment-editor-header">
                                <nav class="gitment-editor-tabs">
                                    <button class="gitment-editor-tab gitment-selected">编辑</button>
                                    <select name="commentStar">
                                        <option value="5" selected>5分</option>
                                        <option value="4">4分</option>
                                        <option value="3">3分</option>
                                        <option value="2">2分</option>
                                        <option value="1">1分</option>
                                    </select>
                                </nav>
                            </div>
                            <div class="gitment-editor-body">
                                <div class="gitment-editor-write-field">
                                    <textarea name="commentInfo" placeholder="你想说些什么" title="Login to Comment"></textarea>
                                </div>
                                <div class="gitment-editor-preview-field gitment-hidden">
                                    <div class="gitment-editor-preview gitment-markdown"></div>
                                </div>
                            </div>
                        </div>
                        <div class="gitment-editor-footer">
                            <button class="gitment-editor-submit" type="submit" title="Login to Comment">评论</button>
                        </div>
                    </div>
                </form>



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