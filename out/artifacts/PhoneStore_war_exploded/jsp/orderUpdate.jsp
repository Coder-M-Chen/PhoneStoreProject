<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script src="${pageContext.request.contextPath}/lib/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugin/jquery.cookie.js"></script>

    <title>订单详情</title>
    <link href="${pageContext.request.contextPath}/css/copy_common.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/copy_main.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/copy_reset.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopCart.css" />
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

<div class="box">

    <div class="shopCart_box comWidth">

            <div class="shop_Message">
                <h3 class="shop_Message_Title">收货信息</h3>
                <div class="shop_Message_text">

                    <div class="adressinfo clearfix">
                        <p><em>*</em>&nbsp; 详细地址:</p>
                        <p class="comment_width"><input type="text" name="address" id="sendAddress" value="<s:property value="#session.addressee.address"/>" placeholder="最多输入26个汉字" readonly/></p>
                    </div>
                    <div class="peopleinfo clearfix">
                        <p><em>*</em>&nbsp; 收 货 人:&nbsp;</p>
                        <p class="comment_width"><input type="text" name="sendOwner" value="<s:property value="#session.addressee.owner"/>" placeholder="最多输入10个汉字" readonly/></p>
                    </div>

                    <div class="phoneinfo clearfix">
                        <p><em>*</em>&nbsp; 手      机:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                        <p class="comment_width"><input type="text" name="sendPhone" id="" value="<s:property value="#session.addressee.phone"/>" placeholder="最多输入10个汉字" readonly/></p>
                    </div>

                </div>
            </div>

            <div class="Delivery_list">
                <h3 class="shop_Message_Title">送货清单
                    <%--<a href="#" class="backshopcar">返回购物车修改</a>--%>
                </h3>
                <table class="Delivery_list_content">
                    <tr class="tr">
                        <td class="lefttd">商品名称</td>
                        <td>单价</td>
                        <td>数量</td>
                        <td>小计</td>
                        <td>操作</td>
                    </tr>
                    <s:iterator value="#session.goodsList.list" var="d">
                        <tr>
                            <td class="lefttd">
                                <div class="left_con clearfix">
                                    <div class="img_area">
                                        <img src="${pageContext.request.contextPath}/images/<s:property value="#d.goodId"/>.png" style="width: 100px;height: 100px;"/>
                                    </div>
                                    <p style="margin-top: 10px;"><s:property value="#d.goodName"/></p>
                                    <p class="td_p"><s:property value="#d.goodSet"/></p>
                                </div>
                            </td>
                            <td>￥ <s:property value="#d.goodPrice"/></td>
                            <td><s:property value="#d.goodCount"/></td>
                            <td class="shop_price">￥<s:property value="#d.goodPrice*#d.goodCount"/></td>
                            <s:if test="#session.orderEntity.orderState=='待评价'.toString()">
                                <td><a href="goods_toAddComment.action?goodId=<s:property value="#d.goodID"/>">评论</a></td>
                            </s:if>
                            <s:elseif test="#session.orderEntity.orderState=='待发货'.toString()">
                                <td>待发货</td>
                            </s:elseif>
                        </tr>
                    </s:iterator>
                </table>
            </div>
            <div class="Order_settlement">
                <h3 class="shop_Message_Title">送货清单</h3>
                <div class="finally_box clearfix" >
                    <div class="fin_text">
                        <p>总计 <span>￥<s:property value="#session.goodsList.orderPrice"/></span></p>
                        <s:if test="#session.userEntity.userType=='A'.toString()">
                            <s:if test="#session.orderEntity.orderState=='待发货'.toString()">
                                <button onclick="window.location.href='order_sendOrder.action'" style="height: 30px;width: 100px;background: #ff0000;color: #FFFFFF;">发货</button>
                            </s:if>
                        </s:if>
                        <s:else>
                            <s:if test="#session.orderEntity.orderState=='待收货'.toString()">
                                <button onclick="window.location.href='order_receiveOrder.action'" style="height: 30px;width: 100px;background: #ff0000;color: #FFFFFF;">收货</button>
                            </s:if>
                            <button onclick="window.location.href='order_findByUserId.action'" style="height: 30px;width: 100px;background: #ff0000;color: #FFFFFF;">返回</button>
                        </s:else>
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