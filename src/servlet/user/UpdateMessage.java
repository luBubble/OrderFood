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
 * Servlet implementation class UpdateMessage
 */
@WebServlet("/UpdateMessage")
public class UpdateMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMessage() {
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
		//bug:修改手机号码和邮箱会导致重复
		User user=new User();
		user=(User)request.getSession().getAttribute("user");
		String uname=request.getParameter("uname");
		String uemail=request.getParameter("uemail");
		String uphone=request.getParameter("uphone");
		//String upicture=request.getParameter("upicture");
		String udescription=request.getParameter("udescription");
		String uaddress=request.getParameter("uaddress");
		user.setUname(uname);
		user.setUphone(uphone);
		user.setUemail(uemail);
		user.setUdescription(udescription);
		user.setUaddress(uaddress);
		PrintWriter out = response.getWriter(); 
		try
		{
			UserDaoImp userDao=new UserDaoImp();
			if(userDao.updateUser(user))
			{
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", user);//存入新的用户信息
				JSONArray result = JSONArray.fromObject(ResultCodeEnum.SUCCESS);//转换为json型数据
				out.print(result);
				System.out.println("更新用户信息成功");
			}
			else
			{
				JSONArray result = JSONArray.fromObject(ResultCodeEnum.ERROR);//转换为json型数据
				out.print(result);
				System.out.println("更新用户信息失败");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
