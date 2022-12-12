package shop.Services.Impl;

import java.util.List;

import shop.DAO.Impl.CategoryDAOImpl;
import shop.Models.CategoryModel;
import shop.Services.ICategoryServices;

public class CategoryServicesImpl implements ICategoryServices{
	
	CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

}
