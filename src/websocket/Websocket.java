package websocket;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import beans.User;
import net.sf.json.JSONObject;

@ServerEndpoint(value = "/Websocket",configurator = GetHttpSessionConfigurator.class)
public class Websocket {
	private Session session;
	private HttpSession httpSession;
	private static HashMap<Integer, Session> onlineUser = new HashMap<Integer, Session>();
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config)
	{
		httpSession=(HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		User user=(User)httpSession.getAttribute("user");
		this.session=session;
		if(user!=null)
		{
			int uid=user.getUid();
			int utype=user.getUtype();
			onlineUser.put(uid, session);
			System.out.println("连接开始，用户id："+uid+" type："+utype);
		}
		else
		{
			System.out.println("用户已掉线，连接中断");
		}
		
	}
	
	@OnClose
	public void OnClose()
	{
		Integer userid=null;
		for(Entry<Integer, Session> entry:onlineUser.entrySet())
		{
			if(entry.getValue().equals(session)||entry.getValue()==session)
			{
				userid=entry.getKey();
			}
		}
		if(userid!=null)
		{
			onlineUser.remove(userid);
			System.out.println("连接结束，用户id："+userid);
		}
	}
	
	@OnMessage
	public void OnMessage(String message)
	{
		System.out.println("收到消息"+message);
		JSONObject json=JSONObject.fromObject(message);//字符串转成json
		String mtype=(String)json.get("type");
		if(mtype.equals("1"))//新订单提醒
		{
			
				int shopid=Integer.parseInt((String) json.get("shopid"));
				int userid=Integer.parseInt((String) json.get("userid"));
				Session shopSession=onlineUser.get(shopid);
				System.out.println("shopid："+shopid);
				shopSession.getAsyncRemote().sendText("{\"type\":\"1\",\"mesg\":\"您有一个新的lll点餐订单\",\"shopid\":\""
				+shopid+"\",\"userid\":\""+userid+"\"}");
		}
		if(mtype.equals("2"))//提醒用户商家接受订单
		{
				int userid=Integer.parseInt((String)json.get("userid"));
				int shopid=Integer.parseInt((String) json.get("shopid"));
				Session userSession=onlineUser.get(userid);
				userSession.getAsyncRemote().sendText("{\"type\":\"2\",\"mesg\":\"您的订单已被接受\",\"shopid\":\""
				+shopid+"\",\"userid\":\""+userid+"\"}");
		}
		if(mtype.equals("3"))//提醒用户商家拒绝订单
		{
				int userid=Integer.parseInt((String)json.get("userid"));
				int shopid=Integer.parseInt((String) json.get("shopid"));
				Session userSession=onlineUser.get(userid);
				userSession.getAsyncRemote().sendText("{\"type\":\"3\",\"mesg\":\"您的订单已被拒绝\",\"shopid\":\""
				+shopid+"\",\"userid\":\""+userid+"\"}");
		}
	}
	
	@OnError
	public void onError(Throwable t)
	{
		Integer userid=null;
		for(Entry<Integer, Session> entry:onlineUser.entrySet())
		{
			if(entry.getValue().equals(session)||entry.getValue()==session)
			{
				userid=entry.getKey();
			}
		}
		if(userid!=null)
		{
			onlineUser.remove(userid);
			System.out.println("连接错误，userid："+userid);
			System.out.println(t.toString());
		}
	}
}
