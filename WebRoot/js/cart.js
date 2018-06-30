var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);

function getAllCartItem()
{
	var tatal="";
	$.ajax({
		 	url : basePath+"/GetAllCart",
		    type : "post",
		    success : function (data)
		    {
		    	 if(data!=null&&data!="")
		    		 {
		    		 var mesgs= JSON.parse(data);//转成js对象
				    	for (var i in mesgs)
						{
				    		var mesg=mesgs[i];
				    		var oiname=mesg.fname;
				    		var oipic=mesg.fpicture;
				    		var oiprice=mesg.fprice;
				    		var oiquantity=mesg.oiquantities;
				    		tatal+=oiprice;
				    		
				    		var orderItem="<li ><div class='food-item'><img src='/OrderFood/img/"+oipic
				    		+"' /><div class='food-text'><h3>"+oiname+"</h3><h4>上等原料食材，精心烘焙，匠心工艺</h4>"
							+"</div><div class='food-price'><h2 style='color:#82573E;'>￥<span>"+oiprice
							+"</span></h2><input id='min' name=''  type='button' value='-' />"+
		    				"<input id='text_box' name='' class='form-control' type='text' value='1' />"+
		    				"<input id='add' name=''  type='button' value='+' />";
				    		
							$(".orderItem-ul").append(orderItem);
						}
				    	$(".tatal").remove();
				    	$(".tatal").append("");
		    		 }
		    	 else
		    		 {
		    		 var text="<h3>购物车内暂无商品~</h3>";
		    		 $(".orderItem-ul").append(text);
		    		 }
		    	
		    },
		    error : function (data)
		    {
		    	var text="<h3>购物车内暂无商品~</h3>";
	    		 $(".orderItem-ul").append(text);
		    }
	});
}
window.onload =getAllCartItem;

$("body").on('click',".creat-order",function(){
	
	layer.msg("成功生成订单",{time: 1000, icon:1});
	layer.msg("向商家发送消息",{time: 1000, icon:1});
	
	 websocket.send(JSON.stringify(
		 {
		 	"type":"1",
		 	"shopid":"12",
		 	"userid":"8"
		 }
		));
	 
});

$("body").on('click',".cancel",function(){
	layer.msg("正在取消...",{time: 1000, icon:1});
	window.history.back(-1); 
});
