package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import dao.inf.UserDao;
import db.DBHelper;

public class UserDaoImp implements UserDao {

	@Override
	public User getUser(String userId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql ="";
		User user=new User();
		try {
			conn = DBHelper.getConnection();
			if (conn != null) {
				if(userId.contains("@"))
				{
					sql="select * from user where uemail='"+userId+"'";
					
				}
				else
				{
					sql="select * from user where uphone='"+userId+"'";
				}
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					System.out.println("找到了uid："+rs.getInt("uid"));
					user.setUid(rs.getInt("uid"));
					user.setUname(rs.getString("uname"));
					user.setUphone(rs.getString("uphone"));
					user.setUemail(rs.getString("uemail"));
					user.setUpicture(rs.getString("upicture"));
					user.setUpw(rs.getString("upw"));
					user.setUtype(rs.getInt("utype"));
					user.setUdescription(rs.getString("udescription"));
					user.setUaddress(rs.getString("uaddress"));
				} else 
				{
					System.out.println("没找到uid");
					return null;
				}
			} else {
				System.out.println("MySQL连接失败");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBHelper.closeAction(ps,rs,null,conn);
		}
		return user;
	}

	@Override
	public User getUserByUid(int uid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql ="";
		User user=new User();
		try {
			conn = DBHelper.getConnection();
			if (conn != null) {
				
				sql="select * from user where uid="+uid;
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					System.out.println("根据uid找用户："+rs.getInt("uid"));
					user.setUid(rs.getInt("uid"));
					user.setUname(rs.getString("uname"));
					user.setUphone(rs.getString("uphone"));
					user.setUemail(rs.getString("uemail"));
					user.setUpicture(rs.getString("upicture"));
					user.setUpw(rs.getString("upw"));
					user.setUtype(rs.getInt("utype"));
					user.setUdescription(rs.getString("udescription"));
					user.setUaddress(rs.getString("uaddress"));
				} else 
				{
					System.out.println("没找到uid");
					return null;
				}
			} else {
				System.out.println("MySQL连接失败");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBHelper.closeAction(ps,rs,null,conn);
		}
		return user;
	}

	@Override
	public boolean addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBHelper.getConnection();
			if (conn != null) {
				String sql = "insert into user(uname,uphone,uemail,upw,utype) values('"+user.getUname()
				+"','"+user.getUphone()+"','"+user.getUemail()+"','"+user.getUpw()+"','"+user.getUtype()+"')";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate(sql);	
				
			} else {
				System.out.println("MySQL连接失败");
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBHelper.closeAction(ps,null,null,conn);
		}
		return true;
	}

	//删除用户或商家
	@Override
	public boolean deleteUser(int uId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="delete from user where uid="+uId;
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

	//修改用户信息
	@Override
	public boolean updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="update user set uname='"+user.getUname()+"',uemail='"+user.getUemail()+
						"',uphone='"+user.getUphone()+"',upw='"
						+user.getUpw()+"',udescription='"+user.getUdescription()+"',uaddress='"+
						user.getUaddress()+"' where uid="+user.getUid();
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
	public boolean isExit(int userId) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				
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
		return false;
	}
//读取商家列表
	@Override
	public List<User> getAllShop() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		List<User> users=new ArrayList<>();
		try
		{
			conn=DBHelper.getConnection();
			if(conn!=null)
			{
				String sql="select * from user where utype=0";
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery(sql);
				if(rs!=null)
				{
					while(rs.next())
					{
						User user=new User();
						user.setUid(rs.getInt("uid"));
						user.setUname(rs.getString("uname"));
						user.setUphone(rs.getString("uphone"));
						user.setUemail(rs.getString("uemail"));
						user.setUpicture(rs.getString("upicture"));
						user.setUtype(rs.getInt("utype"));
						user.setUdescription(rs.getString("udescription"));
						user.setUaddress(rs.getString("uaddress"));
						users.add(user);
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

		return users;
	}

}
