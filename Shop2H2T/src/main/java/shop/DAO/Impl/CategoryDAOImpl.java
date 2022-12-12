package shop.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shop.Connection.DbConnection;
import shop.DAO.ICategoryDAO;
import shop.Models.CategoryModel;

public class CategoryDAOImpl extends DbConnection implements ICategoryDAO{

	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "Select * From Category";
		
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery() ;
			while(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryId(rs.getInt("categoryId"));
				category.setCategoryName(rs.getNString("categoryName"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}

}
