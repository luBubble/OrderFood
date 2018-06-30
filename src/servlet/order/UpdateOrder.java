package servlet.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Food;
import beans.Order;
import dao.imp.FoodDaoImp;
import dao.imp.OrderDaoImp;
import net.sf.json.JSONArray;
import result.ResultCodeEnum;

/**
 * Servlet implementation class updateOrder
 */
@WebServlet("/updateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
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
		int ostatus=Integer.valueOf(request.getParameter("ostatus"));
		OrderDaoImp orderDao=new OrderDaoImp();
		PrintWriter out=response.getWriter();
		try
		{
			Order order=orderDao.getOrder(oid);
			order.setOstatus(ostatus);
			if(orderDao.updateOrder(order))
			{
				JSONArray result=JSONArray.fromObject(ResultCodeEnum.SUCCESS);
				out.print(result);
			}
			else
			{
				JSONArray result=JSONArray.fromObject(ResultCodeEnum.ERROR);
				out.print(result);
			}
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
