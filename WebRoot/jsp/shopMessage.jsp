<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<title>lll-店铺信息</title>

	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- bootstrap -->
	<link rel="stylesheet" href="/OrderFood/css/bootstrap.min.css">
	<!-- font-awesome -->
	<link rel="stylesheet" href="/OrderFood/css/font-awesome.min.css">
	<!-- custom -->
	<link rel="stylesheet" href="/OrderFood/css/templatemo-style.css">
	<link rel="stylesheet" href="/OrderFood/css/user.css">
	<!-- google font -->
	<link href='//fonts.googleapis.com/css?family=Signika:400,300,600,700' rel='stylesheet' type='text/css'>
	<link href='//fonts.googleapis.com/css?family=Chewy' rel='stylesheet' type='text/css'>
</head>
<body id="home" data-spy="scroll" data-target=".navbar-collapse">
<%
	User user=new User();
	user=(User)request.getSession().getAttribute("user");
	int shopid=Integer.valueOf(request.getParameter("shopid"));
%>
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon icon-bar"></span>
					<span class="icon icon-bar"></span>
					<span class="icon icon-bar"></span>
				</button>
				<a  class="navbar-brand smoothScroll"><strong class="top-shop-name" style="color: #82573E"></strong></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#home" class="smoothScroll"></a></li>
					<li><a href="/OrderFood/jsp/index.jsp" class="smoothScroll">首页</a></li>
				<% 
				if(user!=null)
				{
					int uid=user.getUid();
					String myself="/OrderFood/jsp/myself.jsp?shopid="+uid;
					%>
					<span style="color: #ffffff" class="shopmesg-userid"><%out.print(uid);%></span>
					<%
				if(user.getUtype()==1)
				{
				%>
					<li><a  href="/OrderFood/jsp/cart.jsp" class="smoothScroll">购物车</a></li>
					<li><a href="#about" class="smoothScroll">订单管理</a></li>
					<li><a href=<%out.print(myself);%> class="smoothScroll">我的信息</a></li>
				<% 
				}
				else
				{
					%>
					<li><a href="#about" class="smoothScroll">订单管理</a></li>
					<li><a href=<%out.print(myself);%> class="smoothScroll">我的商店</a></li>
					<%
				}
				%>
				
					<li><a href="#gallery" class="smoothScroll out-login">退出登录</a></li>
					<li><a href="#contact" class="smoothScroll delete-user ">注销用户</a></li>
				<%
				}
				else
				{
					%>
					<li><a href="#home" class="smoothScroll"></a></li>
					<li><a href="/OrderFood/jsp/login.jsp" class="smoothScroll">登录</a></li>
					<li><a href="/OrderFood/jsp/register.jsp" class="smoothScroll">注册</a></li>
					<%
				}
				%>
				</ul>
			</div>
		</div>
	</div>
	<!-- end navigation -->
<div class="article">
	<h2 class="text-uppercase text-center">店铺信息</h2>
	<hr>
	<div class="base-mesg"> 
		
	</div>
	
	<div>
	<div class="foods-list">
		<ul class="foods-ul">
			<p>菜单</p>
			<hr style="width: 100%; color:#D5D5D5;"/>
		</ul>
	</div>
	</div>
	
</div>	
	<img src="/OrderFood/img/di.jpg" class="foot-img" alt="foot"/>
	
	<script src="/OrderFood/assets/jquery-3.1.1.min.js" ></script>
	<script src="/OrderFood/assets/layer/layer.js" type="text/javascript"></script>
	<script src="/OrderFood/js/bootstrap.min.js"></script>
	<script src="/OrderFood/js/plugins.js"></script>
	<script src="/OrderFood/js/smoothscroll.js"></script>
	<script src="/OrderFood/js/custom.js"></script>
	<script src="/OrderFood/js/food.js" type="text/javascript"></script>
	<script src="/OrderFood/js/normalAction.js" type="text/javascript"></script>
</body>
</html>