package servlet.user;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.imp.UserDaoImp;
import net.sf.json.JSONArray;
import result.DataResponse;
import result.ResultCodeEnum;

/**
 * Servlet implementation class GetAllShop
 */
@WebServlet("/GetAllShop")
public class GetAllShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllShop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("获取商店列表");
		response.setContentType("text/html;charset=utf-8");//解决乱码
		request.setCharacterEncoding("utf-8");
		List<User> users=new ArrayList<>();
		UserDaoImp userDaoImp=new UserDaoImp();
		PrintWriter out = response.getWriter(); 
		try
		{
			users=userDaoImp.getAllShop();
			if(users!=null)
			{
				response.setContentType("application/json");
				JSONArray jsonArray = JSONArray.fromObject(users);//转换为json型数据
				//向前端返回数据
				out.print(jsonArray);
				System.out.println("读取商店数据"+jsonArray);
				//response.setContentType("application/json");

			}
			else
			{
				System.out.println("无商店列表");
			}
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
