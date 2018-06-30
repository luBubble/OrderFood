package servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cart;
import dao.imp.CartDaoImp;
import net.sf.json.JSONArray;
import result.ResultCodeEnum;

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
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
		int cid=Integer.valueOf(request.getParameter("cid"));
		int cquantity=Integer.valueOf(request.getParameter("cquantity"));
		double cprices=Double.valueOf(request.getParameter("cprices"));
		PrintWriter out=response.getWriter();
		CartDaoImp cartDao=new CartDaoImp();
		Cart cart=new Cart();
		cart.setCquantities(cquantity);
		cart.setCprices(cprices);
		cart.setCid(cid);
		try
		{
			if(cartDao.updateCart(cart))
			{
				JSONArray result = JSONArray.fromObject(ResultCodeEnum.SUCCESS);//转换为json型数据
				out.print(result);
				System.out.println("修改购物车一条记录成功");
			}
			else
			{
				JSONArray result = JSONArray.fromObject(ResultCodeEnum.ERROR);//转换为json型数据
				out.print(result);
				System.out.println("修改购物车一条记录失败");
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
