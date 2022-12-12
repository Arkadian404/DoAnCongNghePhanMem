package shop.Controller.Common;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.Models.CartItemModel;
import shop.Models.ProductModel;
import shop.Services.Impl.ProductServicesImpl;

@WebServlet(urlPatterns = {"/common/product/detail"})
public class ProductDetailController extends HttpServlet{
		
	private static final long serialVersionUID = 1L;
	
	ProductServicesImpl productServicesImpl = new ProductServicesImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int productId = Integer.parseInt(req.getParameter("productId")); 
		ProductModel product = productServicesImpl.find(productId);		
		req.setAttribute("product", product); 
		
		//lay so luong hang nay trong kho
		
		HttpSession session = req.getSession();
		List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute("cart");
		int productQuantityInCart;
		if(cart != null)
		{
			int productIndexInCart = isExisting(productId, cart);
			//chỉ lấy quantity ra chứa không thay đổi quantity product trong cart
			if (productIndexInCart == -1) {
				productQuantityInCart = 1;
			} else {
				productQuantityInCart = cart.get(productIndexInCart).getQuantity();
			}			
		} else {
			productQuantityInCart = 1;
		}
		
		
		req.setAttribute("productQuantityInCart", productQuantityInCart);
		RequestDispatcher rd = req.getRequestDispatcher("/views/common/product.jsp");
		rd.forward(req, resp);
	}
	
	
	private int isExisting(int productId, List<CartItemModel> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductId() == productId) {
				return i;
			}
		}
		return -1;
	}
	
}
