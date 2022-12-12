package shop.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.Connection.DbConnection;
import shop.DAO.IAccountDAO;

public class AccountDAOImpl extends DbConnection implements IAccountDAO{

	@Override
	public boolean authenticateAccount(String username, String password) {
		boolean isValid = false;
		try {
			String sql = "Select * From Account Where username = ? And password = ?";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				isValid = true;
			else
				isValid = false;						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return isValid;
	}

	@Override
	public String findRoleAccount(String username, String password) {
		try {
			String sql = "Select ur.roleName\r\n"
					+ "From Account u Inner Join Admin ur\r\n"
					+ "On u.roleId = ur.roleId\r\n"
					+ "Where u.username = ? And u.password = ?";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				return rs.getNString("roleName");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";		
	}

	@Override
	public void registerAccount(String username, String password) {
		try {
			//2 là user mặc định sau khi đăng ký có role là user
			String sql = "Insert Into Account(username,password,roleId) Values (?,?,2)";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void becomeSeller(int sellerId,String username) {
		try {
			//2 là user mặc định sau khi đăng ký có role là user
			String sql = "Update Account Set sellerid = ?,roleId = 3 Where username = ?";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sellerId);
			ps.setString(2, username);
			ps.executeUpdate();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int findSellerId(String username) {
		try {
			String sql = "Select sellerid From Account Where username = ?";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("sellerid");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int findAccountId(String username) {
		try {
			//2 là user mặc định sau khi đăng ký có role là user
			String sql = "Select userId From Account Where username = ?";
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("userId");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
}
