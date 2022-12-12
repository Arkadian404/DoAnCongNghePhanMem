package shop.DAO.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.Connection.DbConnection;
import shop.DAO.IStoreDAO;

public class StoreDAOImpl extends DbConnection implements IStoreDAO{

	@Override
	public void insertStore(String storeName, Date storeDate) {
		try {
			String sql = "Insert Into Store(storeName,storeDate) Values (?,?)";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, storeName);			
			ps.setDate(2, storeDate);			
			ps.executeUpdate();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int findStoreId(String storeName) {
		try {
			//2 là user mặc định sau khi đăng ký có role là user
			String sql = "Select storeId From Store Where storeName = ?";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, storeName);			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("storeId");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
