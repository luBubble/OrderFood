package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.imp.UserDaoImp;
import net.sf.json.JSONArray;
import result.ResultCodeEnum;

/**
 * Servlet implementation class UpdatePW
 */
@WebServlet("/UpdatePW")
public class UpdatePW extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePW() {
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
		String oldPw=request.getParameter("oldPw");
		String newPw=request.getParameter("newPw");
		User user=(User)request.getSession().getAttribute("user");
		PrintWriter out=response.getWriter();
		if(oldPw.equals(user.getUpw()))
		{
			try
			{
				user.setUpw(newPw);
				UserDaoImp userDao=new UserDaoImp();
				if(userDao.updateUser(user))
				{
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", user);//存入新的用户信息
					JSONArray result=JSONArray.fromObject(ResultCodeEnum.SUCCESS);
					out.print(result);
				}
				else
				{
					JSONArray result=JSONArray.fromObject(ResultCodeEnum.ERROR);
					out.print(result);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else
		{
			JSONArray result=JSONArray.fromObject(ResultCodeEnum.OLDPASSWORD_WRONG);
			out.print(result);
		}
	}

}
