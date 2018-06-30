package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.imp.UserDaoImp;
import net.sf.json.JSONArray;
import result.ResultCodeEnum;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String userId=request.getParameter("userId");
		String pw=request.getParameter("password");
		User user=new User();
		UserDaoImp userDaoImp=new UserDaoImp();
		PrintWriter out=response.getWriter();
		try
		{
			user=userDaoImp.getUser(userId);
			if(user==null)
			{
				JSONArray result=JSONArray.fromObject(ResultCodeEnum.ACCOUNT_NOT_EXISTS);
				out.print(result);
				request.setAttribute("message", "该用户不存在，请注册后登录");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
			}
			else
			{
				if(pw.equals(user.getUpw()))
				{
					String check=request.getParameter("remember");
					if(!"".equals(check)&&check!=null)
					{
						//创建两个Cookie对象
						Cookie nameCookie = new Cookie("userId", userId);
						//设置Cookie的有效期为7天
						nameCookie.setMaxAge(60 * 60 * 24 * 7);
						Cookie pwdCookie = new Cookie("password", pw);
						pwdCookie.setMaxAge(60 * 60 * 24 * 7);
						response.addCookie(nameCookie);
						response.addCookie(pwdCookie);
					}
					System.out.println("登录成功！");
					request.getSession().setAttribute("user", user);
					JSONArray result=JSONArray.fromObject(ResultCodeEnum.SUCCESS);
					System.out.println(result);
					out.print(result);
					response.sendRedirect("/OrderFood/jsp/index.jsp");
				}
				else
				{
					JSONArray result=JSONArray.fromObject(ResultCodeEnum.PASSWORD_WRONG);
					out.print(result);
					request.setAttribute("message", "用户名或密码输入错误，请重新输入");
					request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JSONArray result=JSONArray.fromObject(ResultCodeEnum.ERROR);
			out.print(result);
		}
		
	}

}
