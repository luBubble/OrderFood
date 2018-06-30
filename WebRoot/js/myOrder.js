var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);
function getAllOrder()
{
		$.ajax(
		{
			url:basePath+"/GetAllOrder",
			type:"post",
			dataType:"json",
			success:function(data)
			{
				
				if(data!=null &&data!="")
					{
					var datas=JSON.stringify(data);
					var orders= JSON.parse(datas);//转成js对象
					for (var i in orders)
					{
				        var order = orders[i];
				        var oid=order.oid;
						var shopname=order.shopname;
						var username=order.username;
						var prices=order.oprices;
						var statu=order.ostatus;
						var s="";
						if(statu=="2")
							{
							s="待处理";
							}
						else if(statu=="1")
							{
							s="已完成";
							}
						else if(statu=="0")
						{
						s="被拒绝";
						}
						var otext="<li><div class='order-for-oi'><table ><tr><td class='order-oid'>"+oid+"</td><td>"+shopname+"</td><td>"
						+username+"</td><td>"+prices+"</td><td>"+s+"</td><td style='width: 100px;'>"
						+"<button class='form-control order-delete'>删除</button><br /><button class='form-control look-orderItem'>查看详情</button>"
						+"</td></tr></table ></div></li>";
						$(".order-ul").append(otext);
				    }
					}
				else
					{
						$('body').find(".order-ul").remove();
						var gtext="<div >暂无订单<br/></div>";
						$(".order-ul").append(gtext);
					}
			},
			error : function (data)
			{
				$('body').find(".order-ul").remove();
				var gtext="<div >获取数据失败<br/></div>";
				$(".order-ul").append(gtext);
			}
		});
}
window.onload =getAllOrder;

$("body").on('click',".look-orderItem",function()
		{
			var oid=$ (this).parents(".order-for-oi").find(".order-oid").text();
			layer.msg("正在跳转",{time: 1000, icon:1});
			setTimeout (function ()
			{location.href = "/OrderFood/jsp/orderItem.jsp?oid="+oid;}, 1000);
		});
//删除订单
$("body").on('click',".order-delete",function()
		{
			var oid=$ (this).parents(".order-for-oi").find(".order-oid").text();
			layer.open (
					{
				    title : '删除订单',
				    content:'确认删除此订单？',
				    
				    area : [
				            '100px', '200px'
				    ],
				    btn : [
				            '确认', '取消'
				    ],
				    btn1 : function (index)
				    {
				    	//跳进删除order的servlet
				    	$.ajax({
							url:basePath+"/DeleteOrder",
							type:"post",
							data:
								{
								"oid":oid,
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
		