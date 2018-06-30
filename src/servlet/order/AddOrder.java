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
import beans.OrderItem;
import beans.User;
import dao.imp.FoodDaoImp;
import dao.imp.OrderDaoImp;
import dao.imp.OrderItemDaoImp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import result.ResultCodeEnum;

/**
 * Servlet implementation class addOrder
 */
@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrder() {
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
		
		OrderDaoImp orderDao=new OrderDaoImp();
		OrderItemDaoImp orderItemDao=new OrderItemDaoImp();
		Order order=new Order();
		System.out.println("addOrder");
		PrintWriter out=response.getWriter();
		User user=(User)request.getSession().getAttribute("user");
		int unid=user.getUid();
		int uzid=Integer.valueOf(request.getParameter("shopid"));
		order.setOuzid(uzid);
		order.setOunid(unid);
		int fid=Integer.valueOf(request.getParameter("foodid"));
//		String[] oifids=request.getParameterValues("oifids");
//		String[] oiquantities=request.getParameterValues("oifids");
//		String[] oiprices=request.getParameterValues("oiprices");
		
		try
		{
			FoodDaoImp foodDao=new FoodDaoImp();
			Food food=foodDao.getFood(fid);
			order.setOprices(food.getFprice());
			orderDao.addOrder(order);
			int oid=orderDao.lastOrder();
			if(fid>0)
			{
					OrderItem orderItem=new OrderItem();
//					int oifid=Integer.valueOf(oifids[i]);
//					int oiquantity=Integer.valueOf(oiquantities[i]);
//					int oiprice=Integer.valueOf(oiprices[i]);
					orderItem.setOioid(oid);
					orderItem.setOifid(fid);
					orderItem.setOiquantity(1);
					orderItem.setOiprices(food.getFprice());
					orderItemDao.addOrderItem(orderItem);
			
				JSONObject result = JSONObject.fromObject(ResultCodeEnum.SUCCESS);//转换为json型数据
				result.put("oid",Integer.toString(oid));
				out.print(result);
				System.out.println("新增订单成功");
			}
			else
			{
				JSONArray result = JSONArray.fromObject(ResultCodeEnum.ERROR);//转换为json型数据
				out.print(result);
				System.out.println("新增订单失败");
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
