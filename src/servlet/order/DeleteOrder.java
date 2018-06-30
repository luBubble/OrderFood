package servlet.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Order;
import beans.User;
import dao.imp.FoodDaoImp;
import dao.imp.OrderDaoImp;
import dao.inf.OrderDao;
import net.sf.json.JSONArray;
import result.ResultCodeEnum;

/**
 * Servlet implementation class DeleteOrder
 */
@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrder() {
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
		int oid=Integer.valueOf(request.getParameter("oid"));
		User user=(User)request.getSession().getAttribute("user");
		OrderDaoImp orderDao=new OrderDaoImp();
		PrintWriter out=response.getWriter();
		try
		{
			
				if(orderDao.deleteOrder(oid))
				{
					JSONArray result = JSONArray.fromObject(ResultCodeEnum.SUCCESS);//转换为json型数据
					out.print(result);
					System.out.println("删除菜品成功");
				}
				else
				{
					JSONArray result = JSONArray.fromObject(ResultCodeEnum.ERROR);//转换为json型数据
					out.print(result);
					System.out.println("删除菜品失败");
				}
			
			
		}
		catch(Exception e)
		{
			JSONArray result = JSONArray.fromObject(ResultCodeEnum.ERROR);//转换为json型数据
			out.print(result);
			System.out.println("服务器异常");
			e.printStackTrace();
		}
	}

}
