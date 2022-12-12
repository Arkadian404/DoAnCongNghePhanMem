package shop.DAO;

import java.util.List;

import shop.Models.CategoryModel;

public interface ICategoryDAO {
	List<CategoryModel> findAll();
}
