<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");//解决乱码
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
				<a href="/OrderFood/jsp/login.jsp" class="navbar-brand smoothScroll"><strong style="color: #82573E">欢迎您，请注册~</strong></a>
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
	<div  class="user-register">
		<div class="container">
				<h2 class="text-uppercase text-center">注册</h2>
				<hr>
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<form name="newUser" action="/OrderFood/Register" method="post" onsubmit="return checkRegisterFill()" role="form">
						<div class="col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 user-login-form">
							<input type="text" class="form-control" name="userId"  placeholder="请输入邮箱或手机号"/>
									<br /><br />
							<input type="text" class="form-control" name="name" placeholder="请输入用户名"/> <br /> <br /> 
							<input type="password" class="form-control" name="password"  placeholder="请输入密码"/>
									<br /><br />
							<input type="password" class="form-control" name="confirmPassword" placeholder="再次输入密码"/> <br /> <br />
							<input type="radio" class="user-radio" name="userType" value="shop"/>商家用户 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		     				<input type="radio" class="user-radio" name="userType" value="user"/>普通用户<br /><br />
							<input name="submit" type="submit" class="form-control user-login-submit"  value="注册">
							<br /><br />
							<a href="/OrderFood/jsp/login.jsp">已有账号，返回登录</a>
						</div>
					</form>
					
				</div>
		</div>
</div>
	<%
		//Object message = session.getAttribute("message");
		if (request.getAttribute("message") != null) {
			out.println(request.getAttribute("message"));
			request.removeAttribute("message");
		}
	%>
	
	
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