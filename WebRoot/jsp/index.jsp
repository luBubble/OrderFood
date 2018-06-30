<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lll点餐-首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- bootstrap -->
	<link rel="stylesheet" href="/OrderFood/css/bootstrap.min.css">
	<!-- font-awesome -->
	<link rel="stylesheet" href="/OrderFood/css/font-awesome.min.css">
	<!-- custom -->
	<link rel="stylesheet" href="/OrderFood/css/templatemo-style.css">
</head>

<body id="home" data-spy="scroll" data-target=".navbar-collapse">
	<!-- start navigation -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon icon-bar"></span>
					<span class="icon icon-bar"></span>
					<span class="icon icon-bar"></span>
				</button>
				<a href="#home" class="navbar-brand smoothScroll"><strong style="color: #82573E">
		<%
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
		User user=new User();
		user=(User)request.getSession().getAttribute("user");
		if(user!=null)
		{
			String myself="/OrderFood/jsp/myself.jsp?shopid="+user.getUid();
			if(user.getUtype()==1)
			{
				out.println(user.getUname()+"，您好!</span></strong></a>");
				%>
						</div>
						<div class="collapse navbar-collapse">
							<ul class="nav navbar-nav navbar-right">
							<li><a href="#home" class="smoothScroll"></a></li>
								<li><a href="#home" class="smoothScroll">首页</a></li>
								<li><a href="/OrderFood/jsp/cart.jsp" class="smoothScroll">购物车</a></li>
								<li><a href="/OrderFood/jsp/myOrders.jsp" class="smoothScroll">订单管理</a></li>
								<li><a href=<%out.print(myself);%> class="smoothScroll myself">我的信息</a></li>
								<li><a href="#gallery" class="smoothScroll out-login">退出登录</a></li>
								<li><a href="#contact" class="smoothScroll delete-user">注销用户</a></li>
							</ul>
						</div>
					</div>
				</div>
				<%
			}
			else
			{
				out.println(user.getUname()+"</span></strong></a>");
				%>
				</div>
						<div class="collapse navbar-collapse">
							<ul class="nav navbar-nav navbar-right">
							<li><a href="#home" class="smoothScroll"></a></li>
								<li><a href="#home" class="smoothScroll">首页</a></li>
								<li><a href="/OrderFood/jsp/myOrders.jsp" class="smoothScroll">订单管理</a></li>
								<li><a href=<%out.print(myself);%> class="smoothScroll myself">我的商店</a></li>
								<li><a href="#gallery" class="smoothScroll out-login">退出登录</a></li>
								<li><a href="#contact" class="smoothScroll delete-user">注销商店</a></li>
							</ul>
						</div>
					</div>
				</div>
				<% 
			}
		}
		else
		{
		out.println("欢迎您，请登录~");%>
		</span></strong></a>
		</div>
						<div class="collapse navbar-collapse">
							<ul class="nav navbar-nav navbar-right">
							<li><a href="#home" class="smoothScroll"></a></li>
								<li><a href="/OrderFood/jsp/index.jsp" class="smoothScroll">首页</a></li>
								<li><a href="/OrderFood/jsp/login.jsp" class="smoothScroll">登录</a></li>
								<li><a href="/OrderFood/jsp/register.jsp" class="smoothScroll">注册</a></li>
							</ul>
						</div>
					</div>
				</div>
		<%}%>
		
	<div class="flexslider">
		<ul class="slides">
			<li>
				<img src="/OrderFood/img/coffe.jpg" alt="Coffee Image">
			</li>
			<li>
				<img src="/OrderFood/img/pizza.jpg" alt="Pizza Image">
			</li>
			<li>
				<img src="/OrderFood/img/cake.jpg" alt="Cake Image">
			</li>
			<li>
				<img src="/OrderFood/img/hamburger.jpg" alt="Hamburger Image">
			</li>
		</ul>
	</div>

	<div class="instructionfood">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h2  class="text-center text-uppercase" >推荐菜品</h2>
					<hr >
				</div>
				
				<div class="if-1 col-md-4 col-sm-4">
					<div class="gallery-wrapper">
						<img src="/OrderFood/img/pizza4.jpg" class="img-responsive gallery-img" alt="Pizza4">
						<div class="gallery-des">
							<h3 style="color: #82573E">香菇培根披萨</h3>
							<h5>精选上好食材，用心烘焙</h5>
						</div>
					</div>
				</div>	
				
				<div class="col-md-4 col-sm-4">
					<div class="gallery-wrapper">
						<img src="/OrderFood/img/coffee1.jpg" class="img-responsive gallery-img" alt="coffee1">
						<div class="gallery-des">
							<h3 style="color: #82573E">美式咖啡</h3>
							<h5>源自美国的烹煮工艺，醇香浓厚</h5>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-4">
					<div class="gallery-wrapper">
						<img src="/OrderFood/img/pizza6.jpg" class="img-responsive gallery-img" alt="Pizza6">
						<div class="gallery-des">
							<h3 style="color: #82573E">蓝莓火腿面包</h3>
							<h5>水果和火腿的完美邂逅</h5>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="gallery-wrapper ">
						<img src="/OrderFood/img/cake4.jpg" class="img-responsive gallery-img2" alt="cake4">
						<div class="gallery-des">
							<h3 style="color: #82573E">红豆薄荷蛋糕</h3>
							<h5>浓情蜜意，甜而不腻</h5>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="gallery-wrapper">
						<img src="/OrderFood/img/coffee3.jpg" class="img-responsive gallery-img2" alt="Pizza 5">
						<div class="gallery-des">
							<h3 style="color: #82573E">焦糖玛奇朵</h3>
							<h5>充满艺术气息的咖啡</h5>
						</div>
					</div>
				</div>				
			</div>
		</div>
	</div>
	<!-- end gallery -->
	
	<div class="shoplist">
			<h2  class="text-center text-uppercase">所有商家</h2>
			<hr >
		<ul class="shop-ul">
		</ul>
	</div>
		
<img src="/OrderFood/img/di.jpg" class="foot-img" alt="foot"/>
	

<script src="/OrderFood/assets/jquery-3.1.1.min.js" ></script>
<script src="/OrderFood/assets/layer/layer.js"></script>
<script src="/OrderFood/js/bootstrap.min.js"></script>
<script src="/OrderFood/js/plugins.js"></script>
<script src="/OrderFood/js/smoothscroll.js"></script>
<script src="/OrderFood/js/custom.js"></script>
<script src="/OrderFood/js/index.js" type="text/javascript"></script>
<script src="/OrderFood/js/normalAction.js" type="text/javascript"></script>
</body>
</html>