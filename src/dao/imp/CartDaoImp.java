package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Food;
import beans.User;
import dao.inf.CartDao;
import db.DBHelper;

public class CartDaoImp implements CartDao {

	//添加菜品到购物车
	@Override
	public boolean addCart(Cart cart) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="insert into cart(cfid,cuid,cquantities,cprices) value("+cart.getCfid()+","+cart.getCuid()+
						","+cart.getCquantities()+","+cart.getCprices()+")";
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
	public boolean updateCart(Cart cart) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="update cart set cquantities="+cart.getCquantities()+",cprices="+cart.getCprices()+
						" where cid="+cart.getCid();
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
	public List<Cart> getAllCart(int uid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		List<Cart> carts=new ArrayList<>();
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="select * from cart where cuid="+uid;
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);
				if(rs!=null)
				{
					while(rs.next())
					{
						FoodDaoImp foodDao=new FoodDaoImp();
						Food food=foodDao.getFood(rs.getInt("cfid"));
						Cart cart=new Cart();
						cart.setCid(rs.getInt("cid"));
						cart.setCfid(rs.getInt("cfid"));
						cart.setCuid(rs.getInt("cuid"));
						cart.setCquantities(rs.getInt("cquantities"));
						cart.setCprices(rs.getDouble("cprices"));
						cart.setFname(food.getFname());
						cart.setFpicture(food.getFpicture());
						cart.setFprice(food.getFprice());
						carts.add(cart);
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

		return carts;
	}

	@Override
	public boolean deleteCart(int cid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="delete from cart where cid="+cid;
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

	//添加购物车时判断是否已存在
	@Override
	public Cart getCart(int uid, int fid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		Cart cart=new Cart();
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="select * from cart where cuid="+uid+"and cfid="+fid;
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);
					if(rs.next())
					{
						cart.setCid(rs.getInt("cid"));
						cart.setCfid(rs.getInt("cfid"));
						cart.setCuid(rs.getInt("cuid"));
						cart.setCquantities(rs.getInt("cquantities"));
						cart.setCprices(rs.getDouble("cprices"));
					}
					else
					{
						System.out.println("没找到cartItem");
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

		return cart;
	}

}
