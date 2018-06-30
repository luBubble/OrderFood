package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Food;
import dao.inf.FoodDao;
import db.DBHelper;

public class FoodDaoImp implements FoodDao {

	@Override
	public boolean addFood(Food food) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="insert into food(fuid,fname,fpicture,fprice,fdescription) value("+food.getFuid()+",'"+food.getFname()+
						"','"+food.getFpicture()+"',"+food.getFprice()+",'"+food.getFdescription()+"')";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			}
			else
			{
				System.out.println("数据库连接失败");
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBHelper.closeAction(ps, null, null, conn);
		}
		return true;
	}

	@Override
	public Food getFood(int fid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		Food food=new Food();
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="select * from food where fid="+fid;
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);

					if(rs.next())
					{
						food.setFid(rs.getInt("fid"));
						food.setFuid(rs.getInt("fuid"));
						food.setFname(rs.getString("fname"));
						food.setFpicture(rs.getString("fpicture"));
						food.setFprice(rs.getDouble("fprice"));
						food.setFdescription(rs.getString("fdescription"));
					}
				
				else
				{
					System.out.println("暂无商家");
					return null;
				}
			}
			else
			{
				System.out.println("数据库连接失败");
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBHelper.closeAction(ps, rs, null, conn);
		}

		return food;
	}

	@Override
	public boolean updateFood(Food food) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="update food set fname='"+food.getFname()+"',fpicture='"+food.getFpicture()+
						"',fprice="+food.getFprice()+",fdescription='"+food.getFdescription()+
						"' where fid="+food.getFid();
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			}
			else
			{
				System.out.println("数据库连接失败");
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBHelper.closeAction(ps, null, null, conn);
		}
		return true;
	}

	@Override
	public List<Food> getAllFood(int uid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		List<Food> foods=new ArrayList<>();
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="select * from food where fuid="+uid;
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);
				if(rs!=null)
				{
					while(rs.next())
					{
						Food food=new Food();
						food.setFid(rs.getInt("fid"));
						food.setFuid(rs.getInt("fuid"));
						food.setFname(rs.getString("fname"));
						food.setFpicture(rs.getString("fpicture"));
						food.setFprice(rs.getDouble("fprice"));
						food.setFdescription(rs.getString("fdescription"));
						foods.add(food);
					}
				}
				else
				{
					System.out.println("暂无商家");
					return null;
				}
			}
			else
			{
				System.out.println("数据库连接失败");
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBHelper.closeAction(ps, rs, null, conn);
		}

		return foods;
	}

	@Override
	public boolean deleteFood(int fid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="delete from food where fid="+fid;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate(sql);
			}
			else
			{
				System.out.println("数据库连接失败");
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBHelper.closeAction(ps, null, null, conn);
		}
		return true;
	}

}
