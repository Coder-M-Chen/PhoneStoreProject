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
    <div class="list">
        <span>分类 :</span>
        <ul>
            <li><a href="goods_findAllUser.action?currPage=1">全部</a></li>
            <s:iterator value="#session.typeList" var="d">
                <li><a href="goods_findByTypeId.action?typeId=<s:property value="#d.typeId"/>&currPage=1"><s:property value="#d.typeName"/></a> </li>
            </s:iterator>
        </ul>
    </div>
    <div class="content">
        <div class="box">
            <span>排序 :</span>
            <ul class="ways">

                <li><a href="goods_findAllUser.action?currPage=1">新款</a></li>
                <li><a href="goods_findAllUserTime.action?currPage=1">时间</a></li>
                <li><a href="goods_findAllUserPrice.action?currPage=1">价格</a></li>

            </ul>
        </div>
        <div class="moban1">

            <s:iterator value="list" var="d">
                <div class="rows">
                    <div class="template">
                        <a href="goods_findById.action?goodId=<s:property value="#d.goodsId"/>">
                            <img src="${pageContext.request.contextPath}/images/<s:property value="#d.goodsId"/>.png" style="height: 200px;">
                            <span class="description" ><s:property value="#d.goodsName"/></span>
                            <em class="price">¥<s:property value="#d.goodsPrice"/></em>
                        </a>
                    </div>
                </div>
            </s:iterator>


        </div>
        <div class="pages">
            <ul style="width: 40%;text-align: center;margin-left: 30%;left: 0;">

                <span>第<s:property value="currPage"/> / <s:property value="totalPage"/> 页</span>&nbsp;&nbsp;
                <span>总记录数<s:property value="totalCount"/> &nbsp;&nbsp; 每页显示<s:property value="pageSize"/> 条</span>&nbsp;&nbsp;
                <span>
                    <s:if test="currPage != 1">
                        <a href="${pageContext.request.contextPath}/goods_findAllUser.action?currPage=1">[首页]</a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/goods_findAllUser.action?currPage=<s:property value="currPage-1"/>">[上一页]</a>&nbsp;&nbsp;
                    </s:if>
                    <s:if test="currPage != totalPage">
                        <a href="${pageContext.request.contextPath}/goods_findAllUser.action?currPage=<s:property value="currPage+1"/>">[下一页]</a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/goods_findAllUser.action?currPage=<s:property value="totalPage"/>">[尾页]</a>&nbsp;&nbsp;
                    </s:if>
                </span>
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
<script type="text/html" id="product_template">
    <s:iterator value="list" var="d">
        <div class="rows">
            <div class="template">
                <a href="detail.jsp">
                    <img src="${pageContext.request.contextPath}/images/list-1.jpg" alt="">
                    <span class="description" ><s:property value="#d.goodsName"/></span>
                    <em class="price">¥<s:property value="#d.goodsPrice"/></em>
                </a>
            </div>
        </div>
    </s:iterator>
</script>

<script>
    //初始化页面时判断cookie是否登录
    $(function(){

        //给所有的商品列表添加hover的效果
        $("#main .moban1").on("mouseenter",".template",function(){
            $(this).stop().animate({"margin-top":6},"fast");
        });
        $("#main .moban1").on("mouseleave",".template",function(){
            $(this).stop().animate({"margin-top":12},"fast");
        });
        //点击时tab切换
        $("#cols").on("click",function(){
            $(this).css("color","red").next().css("color","#000");
            $(".moban1").eq(0).hide();
            $(".moban2").eq(0).empty().show();
            arr.forEach(function(curr){
                $(".template2:last").clone(true).show()
                    .appendTo(".moban2")
                    .find("img").attr("src",curr.src)
                    .end().find(".details2").text(curr.descrip)
                    .end().find(".description2").text(curr.detail)
                    .end().find(".price2").text("¥" + curr.price + ".00");
            });
        });
        $("#rows").on("click",function(){
            $(this).css("color","red").prev().css("color","#000");
            $(".moban2").eq(0).hide();
            $(".moban1").eq(0).show();
        });
    });

</script>
</body>
</html>