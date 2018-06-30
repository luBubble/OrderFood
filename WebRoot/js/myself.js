var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);
function getAllMyMesg()
{
	var parament=document.location.toString().split('?')[1];
	var shopid=parament.split('=')[1];
	$.ajax (
			{
			    url : basePath+"/GetAllFood",
			    type : "post",
			    data :
			    {
			        "shopid" : shopid
			    },
			    success : function (data)
			    {
					var mesg= JSON.parse(data);//转成js对象
					var shopName=mesg.shop.uname;
					$(".top-shop-name").append(shopName);
					var shopEmail=mesg.shop.uemail;
					var shopPhone=mesg.shop.uphone;
					var shopAddress=mesg.shop.uaddress;
					var shopDes=mesg.shop.udescription;
					var shopPic=mesg.shop.upicture;
					var shopMesg="<img src='/OrderFood/img/"+shopPic+"'/><div class='base-mesg-text'>"
					+"<h1 id='personal-name'>"+shopName+"</h1><table border='0' cellpadding='25'>"
					+"<tr><td >邮箱：</td><td id='personal-email'>"+shopEmail+"</td></tr><tr><td >电话：</td><td id='personal-phone'>"+shopPhone
					+"</td></tr><tr><td >地址：</td><td id='personal-address'>"+shopAddress+"</td></tr><tr><td >简介：</td><td id='personal-des'>"
					+shopDes+"</td></tr></table ><button class='form-control base-mesg-edit'>编辑资料</button>"
					+"<button class='form-control base-mesg-pw'>修改密码</button></div>";
					$(".base-mesg").append(shopMesg);
					
			    	if(mesg.foods!=null &&mesg.foods!="")
					{
					for (var i in mesg.foods)
					{
				        var food =mesg.foods[i];
				        var fid=food.fid;
				        var fuid=food.fuid;
				        var fname=food.fname;
				        var fpicture=food.fpicture;
				        var fprice=food.fprice;
				        var fdes=food.fdescription;
				        
						var otext="<li><div class='food-item'><img src='/OrderFood/img/"+fpicture+"' />"
					+"<div class='food-text'><h3 id='food-old-name'>"+fname+"</h3><h4 id='food-old-des'>"+fdes+"</h4><h5 style='color:#ffffff'"
					+" class='food-item-fid' id='food-fid'>"+fid+"</h5><h5 style='color:#ffffff' class='food-item-uid'>"
					+fuid+"</h5><h5 style='color:#ffffff' id='food-old-pic'>"+fpicture
					+"</h5></div><div class='food-price'><h2>￥<span id='food-old-price'>"+fprice+"</span></h2></div><div class='food-button'>"
					+"<button class='form-control food-delete'>删除</button><br/><button "
					+"class='form-control food-edit'>编辑</button></div></div></li>";
					$(".foods-ul").append(otext);
				    }
					}
				else
					{
						var gtext="<h2>暂无菜品</h2>";
						$(".foods-ul").append(gtext);
					}
			    },
			    error : function (data)
			    {
			    	alert("服务器异常");
			    }
			});
}
window.onload =getAllMyMesg;

//增加菜品
$ (".food-add").click (function ()
		{
			layer.open({
	        type: 1,
	        title : '新增菜品',
	        shift: 7,
	        shadeClose: true,
	        content: "<div style='width:350px;'><form name='food' method='post' ><div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品名称:</p><input  class='form-control' type='text' id='foodName'/></div>" +
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品图片：</p><input class='form-control' type='text' id='foodPic'/></div>"+
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品价格：</p><input class='form-control' type='text' id='foodPrice'/></div>"+
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品描述：</p><input style='heigth:200px;' class='form-control' type='text' id='foodDes'/>" +
	        "<input style='margin-top:5%;' type='submit' class='form-control add-sure' value='增加'/></form></div>"
			});
			$ (".add-sure").click (function ()
					{
				
				var foodName=document.getElementById("foodName").value;
				var foodPic=document.getElementById("foodPic").value;
				var foodPrice=document.getElementById("foodPrice").value;
				var foodDes=document.getElementById("foodDes").value;
				layer.msg("增加菜品成功",{time: 1000, icon:1});
				$.ajax({
					url:basePath+"/AddFood",
					type:"post",
					data:
						{
							"fname":foodName,
							"fpicture":foodPic,
							"fprice":foodPrice,
							"fdescription":foodDes
						},
					success:function(data)
					{
						layer.msg("增加菜品成功",{time: 1000, icon:1});
					},
					error : function (data)
			        {
			        	layer.msg("增加菜品失败",{time: 1000, icon:0});
			        }
				});
					});
		});
//修改个人信息
$("body").on('click',".base-mesg-edit",function ()
		{
			
			var oldname=document.getElementById("personal-name").innerHTML;
			
			var oldemail=document.getElementById("personal-email").innerHTML;
			var oldphone=document.getElementById("personal-phone").innerHTML;
			var oldaddress=document.getElementById("personal-address").innerHTML;
			var olddes=document.getElementById("personal-des").innerHTML;
			
			layer.open({
	        type: 1,
	        title : '修改资料',
	        shift: 7,
	        shadeClose: true,
	        content: "<div style='width:350px;'><form name='food' method='post' ><div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>用户名/店名:</p><input  class='form-control' type='text' id='new-name' value='"+oldname+"'/></div>" +
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>邮箱地址：</p><input class='form-control' type='text' id='new-email' value='"+oldemail+"'/></div>"+
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>电话号码：</p><input class='form-control' type='text' id='new-phone' value='"+oldphone+"'/></div>"+
	        	"<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>收货地址：</p><input class='form-control' type='text' id='new-address' value='"+oldaddress+"'/></div>"+
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>简介：</p><input style='heigth:200px;' class='form-control' type='text' id='new-des' value='"+olddes+"'/>" +
	        "<input style='margin-top:5%;' type='submit' class='form-control add-sure' value='修改个人信息'/></form></div>"
			});
			$ (".add-sure").click (function ()
					{
				var newname=document.getElementById("new-name").value;
				var newemail=document.getElementById("new-email").value;
				var newphone=document.getElementById("new-phone").value;
				var newaddress=document.getElementById("new-address").value;
				var newdes=document.getElementById("new-des").value;
				layer.msg("成功修改资料",{time: 1000, icon:1});
				$.ajax({
					url:basePath+"/UpdateMessage",
					type:"post",
					data:
						{
						"uname":newname,
						"uemail":newemail,
						"uphone":newphone,
						"udescription":newdes,
						"uaddress":newaddress,
						},
					success:function(data)
					{
						layer.msg("成功修改资料",{time: 1000, icon:1});
					},
					error : function (data)
			        {
			        	layer.msg("修改资料失败",{time: 1000, icon:0});
			        }
				});
					});
		});

//修改密码
$("body").on('click',".base-mesg-pw",function (){
	
	layer.open({
        type: 1,
        title : '修改密码',
        shift: 7,
        shadeClose: true,
        content: "<div style='width:350px;'><form name='food' method='post' ><div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
        	+"<p>旧密码:</p><input  class='form-control' type='password' id='old-pw' /></div>" +
        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
        	+"<p>新密码：</p><input style='heigth:200px;' class='form-control' type='password' id='new-pw' />" +
        "<input style='margin-top:5%;' type='submit' class='form-control edit-pw' value='修改密码'/></form></div>"
		});
	
	$ (".edit-pw").click (function ()
			{
		var oldpw=document.getElementById("old-pw").value;
		var newpw=document.getElementById("new-pw").value;
		layer.msg("成功修改密码",{time: 1000, icon:1});
		$.ajax({
			url:basePath+"/UpdatePW",
			type:"post",
			data:
				{
				"oldPw":oldpw,
				"newPw":newpw,
				},
			success:function(data)
			{
				layer.msg("成功修改密码",{time: 1000, icon:1});
			},
			error : function (data)
	        {
	        	layer.msg("修改密码失败",{time: 1000, icon:0});
	        }
				});
		});
});

//修改菜品资料
$("body").on('click',".food-edit",function (){
	
	var fid=document.getElementById("food-fid").innerHTML;
	var oldname=document.getElementById("food-old-name").innerHTML;
	var oldpic=document.getElementById("food-old-pic").innerHTML;
	var olddes=document.getElementById("food-old-des").innerHTML;
	var oldprice=document.getElementById("food-old-price").innerHTML;
	layer.open({
        type: 1,
        title : '修改菜品信息',
        shift: 7,
        shadeClose: true,
        content:"<div style='width:350px;'><form name='food' method='post' ><div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品名称:</p><input  class='form-control' type='text' id='new-foodName' value='"+oldname+"'/></div>" +
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品图片：</p><input class='form-control' type='text' id='new-foodPic' value='"+oldpic+"'/></div>"+
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品价格：</p><input class='form-control' type='text' id='new-foodPrice' value='"+oldprice+"'/></div>"+
	        "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"
	        	+"<p>菜品描述：</p><input class='form-control' type='text' id='new-foodDes' value='"+olddes+"'/>" +
	        "<input style='margin-top:5%;' type='submit' class='form-control edit-food' value='修改菜品信息'/></form></div>"
			});
	
	$ (".edit-food").click (function ()
			{
		var newname=document.getElementById("new-foodName").value;
		var newpic=document.getElementById("new-foodPic").value;
		var newprice=document.getElementById("new-foodPrice").value;
		var newdes=document.getElementById("new-foodDes").value;
		layer.msg("成功修改菜品信息",{time: 1000, icon:1});
		$.ajax({
			url:basePath+"/UpdateFood",
			type:"post",
			data:
				{
				"fid":fid,
				"fname":newname,
				"fpicture":newpic,
				"fprice":newprice,
				"fdescription":newdes
				},
			success:function(data)
			{
				layer.msg("成功修改菜品信息",{time: 1000, icon:1});
			},
			error : function (data)
	        {
	        	layer.msg("修改菜品信息失败",{time: 1000, icon:0});
	        }
				});
		});
});

//删除菜品
$("body").on('click',".food-delete",function (){
	var fid=document.getElementById("food-fid").innerHTML;
	layer.open (
			{
		    title : '删除菜品',
		    content:'确认删除此菜品？',
		    
		    area : [
		            '100px', '200px'
		    ],
		    btn : [
		            '确认', '取消'
		    ],
		    btn1 : function (index)
		    {
		    	//跳进删除food的servlet
		    	$.ajax({
					url:basePath+"/DeleteFood",
					type:"post",
					data:
						{
						"fid":fid,
						},
					success:function(data)
					{
						layer.msg("删除成功！",{time: 1000, icon:1});
						window.location.reload ();
					},
					error : function (data)
			        {
			        	layer.msg("删除失败！",{time: 1000, icon:0});
			        }
						});
		    	layer.close(index);
		    },
		    btn2 : function (index)
		    {
		    	layer.close(index);
		    }
			});
	
});