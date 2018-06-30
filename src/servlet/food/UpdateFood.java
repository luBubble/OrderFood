package servlet.food;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Food;
import beans.User;
import dao.imp.FoodDaoImp;
import net.sf.json.JSONArray;
import result.ResultCodeEnum;

/**
 * Servlet implementation class UpdateFood
 */
@WebServlet("/UpdateFood")
public class UpdateFood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFood() {
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
		Food food=new Food();
		int fid=Integer.valueOf(request.getParameter("fid"));
		String fname=request.getParameter("fname");
		String fpicture=request.getParameter("fpicture");
		double fprice=Double.valueOf(request.getParameter("fprice"));
		String fdescription=request.getParameter("fdescription");
		FoodDaoImp foodDao=new FoodDaoImp();
		PrintWriter out=response.getWriter();
		try
		{
			food=foodDao.getFood(fid);
			food.setFname(fname);
			food.setFpicture(fpicture);
			food.setFprice(fprice);
			food.setFdescription(fdescription);
			if(foodDao.updateFood(food))
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
