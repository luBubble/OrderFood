var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);

function getAllOrderItem()
{
	var parament=document.location.toString().split('?')[1];
	var oid=parament.split('=')[1];
	var tatal=0;
	$(".orderItem-id").append(oid);
	$.ajax({
		 url : basePath+"/GetAllOrderItem",
		    type : "post",
		    data :
		    {
		        "oid" : oid
		    },
		    success : function (data)
		    {
		    	var mesgs= JSON.parse(data);//转成js对象
		    	for (var i in mesgs)
				{
		    		var mesg=mesgs[i];
		    		var oiname=mesg.fname;
		    		var oipic=mesg.fpicture;
		    		var oiprice=mesg.fprice;
		    		var oiquantity=mesg.oiquantity;
		    		tatal+=oiquantity*oiprice;
		    		var orderItem="<li ><div class='food-item'><img src='/OrderFood/img/"+oipic
		    		+"' /><div class='food-text'><h3>"+oiname+"</h3><h4>上等原料食材，精心烘焙，匠心工艺</h4>"
					+"</div><div class='food-price'><h2 style='color:#82573E;'>￥<span>"+oiprice
					+"</span></h2><span class='span-num' style='color:#82573E;'>"+oiquantity
					+"</span></div></div></li>";
					$(".orderItem-ul").append(orderItem);
				}
		    	$(".tatal").append(tatal);
		    },
		    error : function (data)
		    {
		    
		    }
	});
}
window.onload =getAllOrderItem;

