<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Shoppy Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <!-- Custom Theme files -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!--js-->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
    <!--icons-css-->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <!--Google Fonts-->
    <link href='https://fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
    <!--static chart-->
    <script src="${pageContext.request.contextPath}/js/Chart.min.js"></script>
    <!--//charts-->
    <link href="${pageContext.request.contextPath}/css/demo.css" rel="stylesheet" type="text/css">
</head>
<body style="padding:0px;">
<div class="page-container">
    <div class="left-content">
        <div class="mother-grid-inner">
            <!--header start here-->
            <div class="header-main" style="height:80px">
                <div class="header-left" style="width:650px;">
                    <div class="logo-name" style=" float: left; width: 300px; margin: 0% 3% 0% 2%;">
                        <a href="/index.jsp"> <h1>WHUT Phone</h1>
                            <%--<img id="logo" src="" alt="Logo"/>--%>
                        </a>
                    </div>
                    <!--search-box-->
                    <div class="search-box" style="width:300px;border:none">
                        <form action="goods_find.action" method="post">
                            <select name="key">
                                <option value="1" selected="selected">商品名称</option>
                                <option value="2">商品类型</option>
                            </select>
                            <input type="text" placeholder="Search..." required="" name="keyString" style="height:25px;width:160px;border: 1px solid #ccc;">
                            <input type="submit" value="">
                        </form>
                    </div><!--//end-search-box-->
                    <div class="clearfix"> </div>
                </div>
                <div class="header-right" style="width:auto;">
                    <div class="profile_details">
                        <ul>
                            <li class="dropdown profile_details_drop">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <div class="profile_img">
                                        <span class="prfil-img">
                                            <img src="${pageContext.request.contextPath}/images/<s:property value="#session.userEntity.userId"/>.png">
                                        </span>
                                        <div class="user-name">
                                            <p><s:property value="#session.userEntity.vName"/> </p>
                                            <span>
                                                <s:if test="#session.userEntity.userType=='U'.toString()">
                                                    User
                                                </s:if>
                                                <s:if test="#session.userEntity.userType=='A'.toString()">
                                                    Administrator
                                                </s:if>
                                            </span>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="clearfix"> </div>
            </div>
            <!--header end here-->
            <!-- script-for sticky-nav -->
            <script>
                $(document).ready(function() {
                    var navoffeset=$(".header-main").offset().top;
                    $(window).scroll(function(){
                        var scrollpos=$(window).scrollTop();
                        if(scrollpos >=navoffeset){
                            $(".header-main").addClass("fixed");
                        }else{
                            $(".header-main").removeClass("fixed");
                        }
                    });

                });
            </script>
            <!-- /script-for sticky-nav -->
            <!--inner block start here-->
            <div class="inner-block">
                <div class="chart-main-block">
                    <%--添加内容--%>



                        <div class="container">
                            <form class="contact" action="type_add.action" method="post" id="form">
                                <div class="row clearfix" style="width: 100%">
                                    <div class="lbl">
                                        <label for="name">
                                            类型编号</label>
                                    </div>
                                    <div class="ctrl">
                                        <input type="text" id="name" name="typeId" data-required="true" data-validation="text"
                                               data-msg="Invalid Name" placeholder="请输入类型编号" value="<s:property value="#session.typeCount+1001"/>" readonly>
                                    </div>
                                </div>
                                <div class="row clearfix" style="width: 100%">
                                    <div class="lbl">
                                        <label for="email">
                                            类型名称</label>
                                    </div>
                                    <div class="ctrl">
                                        <input type="text" id="email" name="typeName" data-required="true" data-validation="email"
                                               data-msg="Invalid E-Mail" placeholder="请输入该类型的名称">
                                    </div>
                                </div>
                                <div class="row  clearfix" style="width: 50%">
                                    <input type="submit" name="submit" id="submit" class="submit" value="保存">
                                </div>
                            </form>
                        </div>



                </div>
            </div>
            <!--inner block end here-->
            <!--copy rights start here-->
            <div class="copyrights">
                <p>Copyright &copy; 2016.Company name All rights reserved.</p>
            </div>
            <!--COPY rights end here-->
        </div>
    </div>
    <!--slider menu-->
    <div class="sidebar-menu">
        <div class="logo"> <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> <a href="#"> <span id="logo" ></span>
            <!--<img id="logo" src="" alt="Logo"/>-->
        </a> </div>
        <div class="menu">
            <ul id="menu" >
                <li><a href="${pageContext.request.contextPath}/jsp/personal.jsp"><i class="fa fa-tachometer"></i><span>个人信息</span></a></li>
                <li><a href="${pageContext.request.contextPath}/user_toUpdatePwd.action"><i class="fa fa-tachometer"></i><span>修改密码</span></a></li>
                <s:if test="#session.userEntity.userType=='U'.toString()">
                    <li><a href="${pageContext.request.contextPath}/order_findByUserId.action?currPage=1"><i class="fa fa-tachometer"></i><span>查看订单</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/feedback_toPageAdd.action"><i class="fa fa-tachometer"></i><span>添加反馈</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/user_toPageCheck.action"><i class="fa fa-tachometer"></i><span>管理员认证</span></a></li>
                </s:if>
                <s:if test="#session.userEntity.userType=='A'.toString()">
                    <li><a href="${pageContext.request.contextPath}/goods_findAll.action?currPage=1"><i class="fa fa-tachometer"></i><span>商品管理</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/type_findAll.action?currPage=1"><i class="fa fa-tachometer"></i><span>类型管理</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/feedback_findAll.action?currPage=1"><i class="fa fa-tachometer"></i><span>反馈统计</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/order_findAll.action?currPage=1"><i class="fa fa-tachometer"></i><span>订单统计</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/user_all2Admin.action?currPage=1"><i class="fa fa-tachometer"></i><span>认证管理</span></a></li>
                    <li><a href="${pageContext.request.contextPath}/news_findAll.action?currPage=1"><i class="fa fa-tachometer"></i><span>新闻管理</span></a></li>

                    <li><a href="${pageContext.request.contextPath}/cart_toManagerView.action"><i class="fa fa-tachometer"></i><span>清算统计</span></a></li>
                </s:if>
            </ul>
        </div>
    </div>
    <div class="clearfix"> </div>
</div>
<!--slide bar menu end here-->
<script>
    var toggle = true;

    $(".sidebar-icon").click(function() {
        if (toggle)
        {
            $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
            $("#menu span").css({"position":"absolute"});
        }
        else
        {
            $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
            setTimeout(function() {
                $("#menu span").css({"position":"relative"});
            }, 400);
        }
        toggle = !toggle;
    });
</script>
<!--scrolling js-->
<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<!--//scrolling js-->
<script src="${pageContext.request.contextPath}/js/bootstrap.js"> </script>
<!-- mother grid end here-->
</body>
</html>