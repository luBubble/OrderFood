var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);
function getAllFood()
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
					+"<h1 >"+shopName+"</h1><table border='0' cellpadding='25'>"
					+"<tr><td >邮箱：</td><td>"+shopEmail+"</td></tr><tr><td >电话：</td><td>"+shopPhone
					+"</td></tr><tr><td >地址：</td><td>"+shopAddress+"</td></tr><tr><td >简介：</td><td>"
					+shopDes+"</td></tr></table ></div>";
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
					+"<div class='food-text'><h3>"+fname+"</h3><h4>"+fdes+"</h4><h5 style='color:#ffffff'"
					+" class='food-item-fid'>"+fid+"</h5><h5 style='color:#ffffff' class='food-item-uid'>"
					+fuid+"</h5></div><div class='food-price'><h2>￥<span class='for-cart'>"+fprice+"</span></h2></div><div class='food-button'>"
					+"<button class='form-control buy-now'>立即购买</button><br/><button "
					+"class='form-control add-to-cart'>加入购物车</button></div></div></li>";
					$(".foods-ul").append(otext);
				    }
					}
				else
					{
						var gtext="<h2>该商家暂无菜品</h2>";
						$(".foods-ul").append(gtext);
					}
			    },
			    error : function (data)
			    {
			    	layer.msg("服务器异常",{time: 1000, icon:0});
			    	
			    }
			});
}
window.onload =getAllFood;



//立即购买
$("body").on('click',".buy-now",function()
{
	 	
	var shopid=$(this).parents(".food-item").find(".food-item-uid").text();
	var foodid=$(this).parents(".food-item").find(".food-item-fid").text();
	
	var userid=$(".shopmesg-userid").text();
	
	$.ajax (
			{
			    url : basePath+"/AddOrder",
			    type : "post",
			    
			    data :
			    {
			        "shopid" :shopid,
			        "foodid":foodid
			    },
			    success : function (data)
			    {
			    	var mesg= JSON.parse(data);//转成js对象
			    	if(mesg.code==1000)
			    		{
			    		var oid=mesg.oid;
			    		
			    		layer.msg("正在跳转",{time: 1000, icon:1});
						setTimeout (function ()
						{location.href = "/OrderFood/jsp/sureOrder.jsp?oid="+oid+"&shopid="+shopid;}, 1000);
//			    		layer.msg("成功生成订单",{time: 1000, icon:1});
//			    		layer.msg("向商家发送消息",{time: 1000, icon:1});
//			    		
//			    		 websocket.send(JSON.stringify(
//			    			 {
//			    			 	"type":"1",
//			    			 	"shopid":shopid,
//			    			 	"userid":userid
//			    			 }
//			    			));
			    		}
			    			
			    			
			    },
			    error : function (data)
			    {
			    	layer.msg(mesg.msg,{time: 1000, icon:0});
			    }
			});	    
});

//加入购物车
$("body").on('click',".add-to-cart",function()
		{ 	
			var price=$(this).parents(".food-item").find(".for-cart").text();
			var foodid=$(this).parents(".food-item").find(".food-item-fid").text();
			
			$.ajax({
				 url : basePath+"/AddToCart",
				    type : "post",
				    
				    data :
				    {
				        "fid" :foodid,
				        "price":price
				    },
				    success : function (data)
				    {
				    	layer.msg("购物车添加成功！",{time: 1000, icon:1});
				    },
				    error : function (data)
				    {
				    	layer.msg("购物车添加失败！",{time: 1000, icon:0});
				    }
				    
			});
		})