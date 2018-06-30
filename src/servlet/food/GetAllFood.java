package servlet.food;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Food;
import beans.User;
import dao.imp.FoodDaoImp;
import dao.imp.UserDaoImp;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import result.ResultCodeEnum;

/**
 * Servlet implementation class getAllFood
 */
@WebServlet("/GetAllFood")
public class GetAllFood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllFood() {
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
		System.out.println("kkkkkkkkkk");
		response.setContentType("text/html;charset=utf-8");//解决乱码
		request.setCharacterEncoding("utf-8");
		List<Food> foods=new ArrayList<>();
		User shop=new User();
		UserDaoImp userDao=new UserDaoImp();
		int shopid=Integer.parseInt(request.getParameter("shopid"));
		System.out.println("shopid:"+shopid);
		PrintWriter out=response.getWriter();
		FoodDaoImp foodDao=new FoodDaoImp();
		JSONObject data=new JSONObject();
		try
		{
			shop=userDao.getUserByUid(shopid);
			JSONObject shopJson=JSONObject.fromObject(shop);
			System.out.println("shop"+shop);
			data.put("shop",shopJson);
			System.out.println("shopJson"+shopJson);
			foods=foodDao.getAllFood(shopid);
			if(foods!=null)
			{
				JSONArray foodsJson=JSONArray.fromObject(foods);
				data.put("foods", foodsJson);
			}
			System.out.println(data);
			out.print(data);
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//JSONArray result=JSONArray.fromObject(ResultCodeEnum.ERROR);
			//out.print(result);
		}
		
		
	}

}
