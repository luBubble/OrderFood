<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<title>lll Order Food Login</title>

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
				<a href="/OrderFood/jsp/login.jsp" class="navbar-brand smoothScroll"><strong style="color: #82573E">欢迎您，请登录~</strong></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/OrderFood/jsp/index.jsp" class="smoothScroll">首页</a></li>
					<li><a href="/OrderFood/jsp/login.jsp" class="smoothScroll">登录</a></li>
					<li><a href="/OrderFood/jsp/register.jsp" class="smoothScroll">注册</a></li>
				</ul>
			</div>
		</div>
	</div>

<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userId ="";
		String password ="";
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			//循环获取cookie值
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
				}
				if(cookie.getName().equals("password"))
				{
					password = cookie.getValue();
				}
			}
		}	
		pageContext.setAttribute("oldUserId", userId);
		pageContext.setAttribute("oldPassword",password);
	%>
		
	<div  class="user-login">
		<div class="container">
				<h2 class="text-uppercase text-center">登录</h2>
				<hr>
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<form name="user"  method="post" onsubmit="return checkFill ()" action="/OrderFood/Login" role="form">
						<div class="col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 user-login-form">
							<input type="text" class="form-control" name="userId" value="${oldUserId }" placeholder="请输入邮箱或手机号"/>
									<br /><br />
							<input type="password" class="form-control" name="password" value="${oldPassword }" placeholder="请输入密码"/>
									<br /><br />
							<input type="checkbox"  name="remember" />七天内记住登录账户与密码
									<br /><br />
							<input name="submit" type="submit" class="login-submit form-control"  value="登录">
							<br /><br />
							<!-- 前往注册界面-->
							<a href="/OrderFood/jsp/register.jsp">没有账号，前往注册</a>
							<br /><br />
							<p>
							<%
		//Object message = session.getAttribute("message");
		if (request.getAttribute("message") != null) {
			out.println(request.getAttribute("message"));
			request.removeAttribute("message");
		}
	%>
							</p>
						</div>
					</form>
					
				</div>
		</div>
</div>
	

	<img src="/OrderFood/img/di.jpg" class="foot-img" alt="foot"/>
	
	<script src="/OrderFood/js/login.js" ></script>
	<script src="/OrderFood/js/jquery.js" ></script>
	<script src="/OrderFood/js/bootstrap.min.js" ></script>
	<script src="/OrderFood/js/plugins.js" ></script>
	<script src="/OrderFood/js/smoothscroll.js" ></script>
	<script src="/OrderFood/js/custom.js" ></script>	
	<script src="/OrderFood/assets/jquery-3.1.1.min.js" ></script>
	<script src="/OrderFood/assets/layer/layer.js" type="text/javascript"></script>
</body>
</html>