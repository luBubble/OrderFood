package dao.inf;

import java.sql.SQLException;
import java.util.List;

import beans.Food;

public interface FoodDao {
	public boolean addFood(Food food) throws SQLException;
	public boolean updateFood(Food food) throws SQLException;
	public List<Food> getAllFood(int uid) throws SQLException;
	public boolean deleteFood(int fid) throws SQLException;
	public Food getFood(int fid) throws SQLException;
}
