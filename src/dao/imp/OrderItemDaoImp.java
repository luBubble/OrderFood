package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cart;
import beans.Food;
import beans.OrderItem;
import dao.inf.OrderItemDao;
import db.DBHelper;

public class OrderItemDaoImp implements OrderItemDao {

	@Override
	public boolean addOrderItem(OrderItem orderItem) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="insert into orderitem(oioid,oifid,oiquantity,oiprices) value("
			+orderItem.getOioid()+","+orderItem.getOifid()+","+orderItem.getOiquantity()+","+
						orderItem.getOiprices()+")";
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
	public List<OrderItem> getAllOrderItem(int oid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		List<OrderItem> orderItems=new ArrayList<>();
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="select * from orderItem where oioid="+oid;
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);
				if(rs!=null)
				{
					while(rs.next())
					{
						FoodDaoImp foodDao=new FoodDaoImp();
						Food food=foodDao.getFood(rs.getInt("oifid"));
						OrderItem orderItem=new OrderItem();
						
						orderItem.setOiid(rs.getInt("oiid"));
						orderItem.setOioid(rs.getInt("oioid"));
						orderItem.setOifid(rs.getInt("oifid"));
						orderItem.setOiquantity(rs.getInt("oiquantity"));
						orderItem.setOiprices(rs.getDouble("oiprices"));
						orderItem.setFname(food.getFname());
						orderItem.setFprice(food.getFprice());//单价
						orderItem.setFpicture(food.getFpicture());
						orderItems.add(orderItem);
						
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

		return orderItems;
	}

	

	

}
