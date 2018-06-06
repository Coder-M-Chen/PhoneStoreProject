<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    <script src="${pageContext.request.contextPath}/lib/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/js/cart.js"></script>
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
<div class="cart-box">
    <div class="cart-nav">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cart-1.jpg" alt=""></li>
        </ul>
    </div>
    <div class="product-box">
        <div class="product-head">
            <ul>
                <li><a href="cart_checkAllByUserId.action">全选</a></li>
                <li>商品名称</li>
                <li>成交价</li>
                <li>数量</li>
                <li>小计</li>
                <li>操作</li>
            </ul>
        </div>
        <div class="product-info">
            <s:iterator value="#session.goodsList.list" var="d">
                <ul>
                    <li>
                        <s:if test="#d.goodCheck=='T'">
                            <a href="cart_checkGood.action?currCartId=<s:property value="#d.cartId"/>">已选</a>
                        </s:if>
                        <s:elseif test="#d.goodCheck=='F'">
                            <a href="cart_checkGood.action?currCartId=<s:property value="#d.cartId"/>">未选</a>
                        </s:elseif>
                    </li>
                    <li>
                        <div class="productId">
                            <div class="imgbox">
                                <img src="${pageContext.request.contextPath}/images/<s:property value="#d.goodId"/>.png" alt="">
                            </div>
                            <div class="description">
                                <a href="goods_findById.action?goodId=<s:property value="#d.goodId"/>">
                                    <s:property value="#d.goodName"/>
                                </a>
                                <br>
                                <s:property value="#d.goodSet"/>
                            </div>
                        </div>
                    </li>
                    <li><div class="price"><s:property value="#d.goodPrice"/></div></li>
                    <li style="text-align: center;margin-top: 30px;">
                        <a href="cart_subGoodCount.action?currCartId=<s:property value="#d.cartId"/>">-</a>
                        <input type="text" id="amount" name="goodCount" value="<s:property value="#d.goodCount"/>">
                        <a href="cart_addGoodCount.action?currCartId=<s:property value="#d.cartId"/>">+</a>
                    </li>
                    <li style="text-align: center;margin-top: 30px;"><div class=""><s:property value="#d.goodCount*#d.goodPrice"/></div></li>
                    <li><div class="handle"><a href="cart_delete.action?currCartId=<s:property value="#d.cartId"/>" class="dele">删除</a></div></li>
                </ul>
            </s:iterator>
        </div>
        <div class="product-foot">
            <ul>
                <li></li>
                <li><div></div>合计:<s:property value="#session.goodsList.orderPrice"/></div></li>
                <li><div></div></li>
                <li>
                    <div>
                        <button onclick="window.location.href='order_toCheckOrder.action'" style="height: 30px;width: 100px;background: #ff0000;color: #FFFFFF;">去结算</button>
                        <%--<a href="order_toCheckOrder.action" id="result">去结算</a>--%>
                    </div>
                </li>
            </ul>
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