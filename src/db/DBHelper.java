package db;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

//与MySQL连接

public class DBHelper {
	public static Connection getConnection() throws Exception {
		// 从properties中获取数据库连接用户密码信息
		Properties prop = new Properties();
		try {
			prop.load(DBHelper.class.getResourceAsStream("/mySQL.properties"));
		} catch (IOException e) {
			System.out.println("查找properties文件出错");
		}
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		Connection conn = null;
		try {
			Class.forName(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	// 关闭连接
	public static void closeAction(PreparedStatement ps, ResultSet rs,Statement st, Connection conn) {
		try {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}
			}
			if (st != null)
			{
				try
				{
					st.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBHelper.getConnection();
			if (conn != null)
			{
				System.out.println("成功连接数据库！");
			}
			else
			{
				System.out.println("连接数据库失败");
			}
			DBHelper.closeAction(null, null, null, conn);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
