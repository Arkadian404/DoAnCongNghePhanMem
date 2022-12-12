package shop.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.Connection.DbConnection;
import shop.DAO.IIventoryDAO;

public class InventoryDAOImpl extends DbConnection implements IIventoryDAO{

	@Override
	public void insertInventory(int productId, int storeId, int amount) {
		String sql = "Insert Into Inventory(id_Inventory_Store,id_Inventory_Product,amount) Values(?,?,?)";
		try {
			Connection conn = super.getConnection();//getConnetion ket noi db
			PreparedStatement ps = conn.prepareStatement(sql);//ném câu sql vào để thực thi
			ps.setInt(1, storeId);
			ps.setInt(2, productId);
			ps.setInt(3, amount);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteInventory(int productId, int storeId) {
		String sql = "Delete From Inventory Where id_Inventory_Store = ? And id_Iventory_Product = ?";
		try {
			Connection conn = super.getConnection();//getConnetion ket noi db
			PreparedStatement ps = conn.prepareStatement(sql);//ném câu sql vào để thực thi
			ps.setInt(1, storeId);
			ps.setInt(2, productId);						
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateInventory(int productId, int storeIdint, int amount) {
		
	}

	@Override
	public int findAmount(int productId) {
		String sql = "Select amount From Inventory Where id_Inventory_Product = ?";
		try {
			Connection conn = super.getConnection();//getConnetion ket noi db
			PreparedStatement ps = conn.prepareStatement(sql);//ném câu sql vào để thực thi
			ps.setInt(1, productId);			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getInt("amount");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
