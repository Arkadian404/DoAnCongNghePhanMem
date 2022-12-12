package shop.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import shop.Connection.DbConnection;
import shop.DAO.ICartItemDAO;
import shop.Models.CartItemModel;

public class CartItemDAOImpl extends DbConnection implements ICartItemDAO {

	@Override
	public void insertToCart(CartItemModel cartItem) {
		String sql = "Insert Into CartItem(quantity,unitPrice,productId,cartId) Values(?,?,?,?)";
		try {
			Connection conn = super.getConnection();//getConnetion ket noi db
			PreparedStatement ps = conn.prepareStatement(sql);//ném câu sql vào để thực thi
			ps.setInt(1, cartItem.getQuantity());
			ps.setFloat(2, cartItem.getUnitPrice());
			ps.setInt(3, cartItem.getProductId());
			ps.setInt(4, cartItem.getCartId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CartItemModel> findAllByCartId() {
		return null;
	}

}
