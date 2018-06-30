package dao.inf;

import java.sql.SQLException;
import java.util.List;

import beans.Cart;

public interface CartDao {
		public boolean addCart(Cart cart) throws SQLException;
		public boolean updateCart(Cart cart) throws SQLException;
		public List<Cart> getAllCart(int uid) throws SQLException;
		public boolean deleteCart(int cid) throws SQLException;
		public Cart getCart(int uid,int fid) throws SQLException;
}
