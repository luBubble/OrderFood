var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);
var websocket = null;


function getAllShop()
{
		$.ajax(
		{
			url:basePath+"/GetAllShop",
			type:"post",
			dataType:"json",
			contentType: "application/json",
			success:function(data)
			{
				
				if(data!=null &&data!="")
					{
					var datas=JSON.stringify(data);//转成字符串
					var shops= JSON.parse(datas);//转成js对象
					for (var i in shops)
					{
				        var shop = shops[i];
				        var shopId=shop.uid;
						var shopName=shop.uname;
						var shopPic=shop.upicture;
						var shopDes=shop.udescription;
						var shopAddress=shop.uaddress;
						
						var otext="<li><div class='shop'><img src='"+basePath+"/img/" + shopPic +"'  alt='shop'/>"
					+"<div style='color:#82573E' class='shop-name'>"+shopName+"<span class='shop-id' style='color:#ffffff'>"+shopId+"</span></div>"
					+"<div class='shop-address' style='color:#A8A8A8'>地址："+shopAddress+"</div>"
					+"<div class='shop-description'>"+shopDes+"</div><button class='form-control shop-into'>进入店铺</button></div></li>";
						
						$(".shop-ul").append(otext);
				    }
					}
				else
					{
						$('body').find(".shop-ul").remove();
						var gtext="<div >暂无商店<br/></div>";
						$(".shoplist").append(gtext);
					}
			},
			error : function (data)
			{
				$('body').find(".shop-ul").remove();
				var gtext="<div >获取数据失败<br/></div>";
				$(".shoplist").append(gtext);
			}
		});
}
window.onload =getAllShop;

$("body").on('click',".shop-into",function()
{
	var shopId=$ (this).parents(".shop").find(".shop-id").text();
	layer.msg("正在跳转",{time: 1000, icon:1});
	setTimeout (function ()
	{location.href = "/OrderFood/jsp/shopMessage.jsp?shopid="+shopId;}, 1000);
})



