package servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.imp.UserDaoImp;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		//路径
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		String userId=request.getParameter("userId");
		String userType=request.getParameter("userType");
		UserDaoImp userDaoImp=new UserDaoImp();
		System.out.println("进入servelet----"+request.getParameter("name"));
		System.out.println("path:"+path+"---basePath:"+basePath);
		User user=new User();
		user.setUname(request.getParameter("name"));
		user.setUpw(request.getParameter("password"));
		if(userId.contains("@"))//邮箱注册
		{
			user.setUemail(userId);
		}
		else
		{
			user.setUphone(userId);
		}
		if(userType.equals("shop"))//商家用户
		{
			user.setUtype(0);
		}
		else if(userType.equals("user"))//普通用户
		{
			user.setUtype(1);
		}
		try
		{
			if(userDaoImp.getUser(userId)!=null)
			{
				//账号已存在
				System.out.println("账号已存在");
				request.setAttribute("message", "该账号已被注册，请重试~");
				request.getRequestDispatcher("./jsp/register.jsp").forward(request,response); 
				//response.sendRedirect("./jsp/register.jsp");
			}
			else
			{
				if(userDaoImp.addUser(user))
				{
					//注册成功
					System.out.println("注册成功");
					User u=userDaoImp.getUser(userId);
					System.out.println("uid:"+u.getUid());
					user.setUid(u.getUid());
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", user);
					//request.getRequestDispatcher("./jsp/index.jsp").forward(request,response); 
					//转发，地址不变，刷新回到原来的页面
					response.sendRedirect("./jsp/index.jsp");
				}	
				else
				{
					System.out.println("注册失败");
					request.setAttribute("message", "注册失败，请重试~");
					request.getRequestDispatcher("./jsp/register.jsp").forward(request,response); 
					//response.sendRedirect("./jsp/register.jsp");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
