
var pathName = window.document.location.pathname;
var basePath = pathName.substring (0, pathName.substr (1).indexOf ('/') + 1);
var websocket=null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
  websocket = new WebSocket("ws://localhost:8080/OrderFood/Websocket");
}
else 
{
  alert('当前浏览器 Not support websocket')
}

websocket.onopen = function () {
	console.log("正在联系商家...");
	//layer.msg("正在联系商家...",{time: 2000, icon:1});
};

//连接发生错误的回调方法
websocket.onerror = function () {
	console.log("WebSocket连接发生错误");
};
//收到消息的处理方法
websocket.onmessage=function (res){
	console.log(res);
	 var resp= JSON.parse(res.data);//转成js对象
	 console.log(resp.type+resp.mesg);
	 var shopid=resp.shopid;
	 var userid=resp.userid;
	 if(resp.type=="1")
	{
		 layer.open (
						{
					    title : '新订单！',
					    content:'您有一个新订单，是否接受？',
					    
					    area : [
					            '100px', '200px'
					    ],
					    btn : [
					            '接受', '拒绝'
					    ],
					    btn1 : function (index)
					    {
					    	websocket.send(JSON.stringify(
					    			 {
					    			 	"type":"2",
					    			 	"shopid":shopid,
					    			 	"userid":userid
					    			 }
					    			));
					    	layer.close(index);
					    	layer.msg("成功接受订单！",{time: 2000, icon:1});
					    	
					    },
					    btn2 : function (index)
					    {
					    	websocket.send(JSON.stringify(
					    			 {
					    			 	"type":"3",
					    			 	"shopid":shopid,
					    			 	"userid":userid
					    			 }
					    			));
					    	layer.msg("成功拒绝订单！",{time: 2000, icon:1});
					    }
						});
	}
	 if(resp.type=="2")
	{
		 layer.msg("商家已接受您的订单",{time: 2000, icon:1});
	}
	 if(resp.type=="3")
	{
		 layer.msg("商家拒绝了您的订单",{time: 2000, icon:0});
	}
};

$ (".out-login").click (function ()
		{
		$.ajax({
		url : basePath+"/OutLogin",
        type : "post",
        success : function (data)
        {
        	layer.msg("退出成功",{time: 1000, icon:1});
        	setTimeout (function ()
        	{location.href = "/OrderFood/jsp/index.jsp";}, 1000);
        },
        error : function (data)
        {
        	layer.msg("退出失败",{time: 1000, icon:0});
        }
		});
		});
//注销用户
$ (".delete-user").click (function ()
		{
		$.ajax({
		url : basePath+"/DeleteUser",
        type : "post",
        success : function (data)
        {
        		layer.msg("注销成功",{time: 1000, icon:1});
            	setTimeout (function ()
            	{location.href = "/OrderFood/jsp/index.jsp";}, 1000);
        	
        },
        error : function (data)
        {
        	layer.msg("注销失败",{time: 1000, icon:0});
        }
		});
		});
