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
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
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
		User user=(User)request.getSession().getAttribute("user");
		PrintWriter out=response.getWriter();
		try
		{
			UserDaoImp userDao=new UserDaoImp();
			userDao.deleteUser(user.getUid());
			request.getSession().removeAttribute("user");
			JSONArray result = JSONArray.fromObject(ResultCodeEnum.SUCCESS);//转换为json型数据
			out.print(result);
			System.out.println("注销用户成功");
		}
		catch(Exception e)
		{
			JSONArray result = JSONArray.fromObject(ResultCodeEnum.ERROR);//转换为json型数据
			out.print(result);
			System.out.println("更新用户信息失败");
			e.printStackTrace();
		}
	}

}
