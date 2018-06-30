package dao.inf;

import java.sql.SQLException;
import java.util.List;

import beans.OrderItem;

public interface OrderItemDao {
	public boolean addOrderItem(OrderItem orderItem) throws SQLException;
	public List<OrderItem> getAllOrderItem(int oid) throws SQLException;
}
