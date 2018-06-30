package servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cart;
import beans.OrderItem;
import beans.User;
import dao.imp.CartDaoImp;
import dao.imp.OrderItemDaoImp;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetAllCart
 */
@WebServlet("/GetAllCart")
public class GetAllCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllCart() {
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
		System.out.println("读取购物车列表");
		response.setContentType("text/html;charset=utf-8");//解决乱码
		request.setCharacterEncoding("utf-8");
		List<Cart> carts=new ArrayList<>();
		User user=(User)request.getSession().getAttribute("user");
		PrintWriter out=response.getWriter();
		CartDaoImp cartDao=new CartDaoImp();
		try
		{
			carts=cartDao.getAllCart(user.getUid());
			if(carts!=null)
			{
				JSONArray data=JSONArray.fromObject(carts);
				out.print(data);
			}
			else
			{
				System.out.println("空购物车");
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
