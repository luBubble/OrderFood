package servlet.order;

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
import beans.Order;
import beans.User;
import dao.imp.FoodDaoImp;
import dao.imp.OrderDaoImp;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetAllOrder
 */
@WebServlet("/GetAllOrder")
public class GetAllOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllOrder() {
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
		response.setContentType("text/html;charset=utf-8");//解决乱码
		request.setCharacterEncoding("utf-8");
		List<Order> orders=new ArrayList<>();
		User user=(User)request.getSession().getAttribute("user");
		PrintWriter out=response.getWriter(); 
		OrderDaoImp orderDao=new OrderDaoImp();
		try
		{
			orders=orderDao.getAllOrder(user.getUid(), user.getUtype());
			if(orders!=null)
			{
				JSONArray data=JSONArray.fromObject(orders);
				out.print(data);
			}
			else
			{
				//JSONArray result=JSONArray.fromObject(ResultCodeEnum.DATA_EMPTY);
				//out.print(result);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//JSONArray result=JSONArray.fromObject(ResultCodeEnum.ERROR);
			//out.print(result);
		}
		
	}

}
