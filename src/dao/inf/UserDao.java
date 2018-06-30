package dao.inf;

import java.sql.SQLException;
import java.util.List;

import beans.User;

public interface UserDao {
	public boolean addUser(User user) throws SQLException;//增加用户
	public User getUser(String userId) throws SQLException;//根据手机号码或邮箱查找其他信息
	public boolean deleteUser(int uId) throws SQLException;//删除用户
	public boolean updateUser(User newUser) throws SQLException;//修改用户信息
	public List<User> getAllShop() throws SQLException;//读取商家信息
	public boolean isExit(int userId) throws SQLException;//判断用户账号是否存在
	public User getUserByUid(int uid) throws SQLException;//根据uid查找其他信息
}
