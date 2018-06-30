package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Order;
import beans.User;
import dao.inf.OrderDao;
import db.DBHelper;

public class OrderDaoImp implements OrderDao {

	@Override
	public int lastOrder() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			if (conn != null) {
				String sql = "select * from `order` order by oid desc limit 1";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getInt("oid");
				} else {
					return 0;
				}
			} else {
				System.out.println("MySQL连接失败");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBHelper.closeAction(ps, rs, null, conn);
		}
		return 0;
	}

	@Override
	public boolean addOrder(Order order) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="insert into `order`(ouzid,ounid,oprices) values("+order.getOuzid()+
						","+order.getOunid()+","+order.getOprices()+")";
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
	public boolean updateOrder(Order order) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="update `order` set ostatus="+order.getOstatus()+",odelete="
			+order.getOdelete()+" where oid="+order.getOid();
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
	public List<Order> getAllOrder(int uid, int utype) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		List<Order> orders=new ArrayList<>();
		UserDaoImp userDao=new UserDaoImp();
		String sql="";
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				if(utype==0)
				{
					sql="select * from `order` where ouzid="+uid;
				}
				else
				{
					sql="select * from `order` where ounid="+uid;
				}
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);
				if(rs!=null)
				{
					while(rs.next())
					{
						Order order=new Order();
						User user=new User();
						User shop=new User();
						order.setOid(rs.getInt("oid"));
						order.setOuzid(rs.getInt("ouzid"));//商家用户
						order.setOunid(rs.getInt("ounid"));
						order.setOprices(rs.getDouble("oprices"));
						order.setOstatus(rs.getInt("ostatus"));
						order.setOdelete(rs.getInt("odelete"));
						shop=userDao.getUserByUid(rs.getInt("ouzid"));
						user=userDao.getUserByUid(rs.getInt("ounid"));
						order.setShopname(shop.getUname());
						order.setUsername(user.getUname());
						orders.add(order);
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

		return orders;
	}

	@Override
	public Order getOrder(int oid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		Order order=new Order();
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="select * from `order` where oid="+oid;
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);
					if(rs.next())
					{
						order.setOid(rs.getInt("oid"));
						order.setOuzid(rs.getInt("ouzid"));//商家用户
						order.setOunid(rs.getInt("ounid"));
						order.setOprices(rs.getDouble("oprices"));
						order.setOstatus(rs.getInt("ostatus"));
						order.setOdelete(rs.getInt("odelete"));
					}
					else
					{
						System.out.println("没找到order");
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

		return order;
	}

	@Override
	public boolean deleteOrder(int oid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="delete from `order` where oid="+oid;
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
