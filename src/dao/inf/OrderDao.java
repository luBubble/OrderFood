package dao.inf;

import java.sql.SQLException;
import java.util.List;

import beans.Order;

public interface OrderDao {
	public boolean addOrder(Order order) throws SQLException;
	public boolean updateOrder(Order order) throws SQLException;//更新状态和删除信息
	public List<Order> getAllOrder(int uid,int utype) throws SQLException;
	public Order getOrder(int oid) throws SQLException;
	public boolean deleteOrder(int oid) throws SQLException;
	public int lastOrder() throws SQLException;
}
