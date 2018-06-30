package servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cart;
import beans.User;
import dao.imp.CartDaoImp;
import net.sf.json.JSONObject;
import result.ResultCodeEnum;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
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
		int fid=Integer.valueOf(request.getParameter("fid"));
		double price=Integer.valueOf(request.getParameter("price"));
		PrintWriter out=response.getWriter();
		User user=(User)request.getSession().getAttribute("user");
		int uid=user.getUid();
		Cart cart=new Cart();
		cart.setCfid(fid);
		cart.setCprices(price);
		cart.setCquantities(1);
		cart.setCuid(uid);
		try
		{
			CartDaoImp cartDao=new CartDaoImp();
			if(cartDao.addCart(cart))
			{
				JSONObject result = JSONObject.fromObject(ResultCodeEnum.SUCCESS);
				out.print(result);
				System.out.println("成功加入购物车");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
