package shop.Controller.Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.Models.CartItemModel;
import shop.Models.ProductModel;
import shop.Services.Impl.ProductServicesImpl;

@WebServlet(urlPatterns = {"/common/cart"})
public class CartController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	ProductServicesImpl productService = new ProductServicesImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		if (action == null) {
			showCartItems(req, resp);
		} else {
			if (action.equalsIgnoreCase("addToCart")) {
				addToCart(req, resp);
			} else if (action.equalsIgnoreCase("remove")) {
				remove(req, resp);
			} else if (action.equalsIgnoreCase("updateToCart")) {
				//nhận request từ trang product.jsp(detail) thì quantity sẽ là nhận từ trang đó luôn
				updateToCart(req,resp);
			}
		}
	}

	private void updateToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("cart") == null) { //tạo cart mới có 1 product duy nhất
			List<CartItemModel> cart = new ArrayList<CartItemModel>();
			int productId = Integer.parseInt(req.getParameter("productId"));
			int productQuantity = Integer.parseInt(req.getParameter("productQuantity"));
			cart.add(new CartItemModel(productService.find(productId), productQuantity));
			session.setAttribute("cart", cart);
		} else { //ép kiểu
			List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute("cart");
			int productId = Integer.parseInt(req.getParameter("productId"));
			int productQuantity = Integer.parseInt(req.getParameter("productQuantity"));
			int productIndexInCart = isExisting(productId, cart);
			if (productIndexInCart == -1) {
				cart.add(new CartItemModel(productService.find(productId), productQuantity));
			} else {
				/* int quantity = cart.get(productIndexInCart).getQuantity() + 1; */
				int quantity = productQuantity;
				cart.get(productIndexInCart).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
			req.setAttribute("cart", cart);
		}
		resp.sendRedirect(req.getContextPath() + "/views/common/cart.jsp");
	}

	private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute("cart");
		int productId = Integer.parseInt(req.getParameter("productId"));
		int productIndexInCart = isExisting(productId, cart);
		cart.remove(productIndexInCart);
		session.setAttribute("cart", cart);
		resp.sendRedirect(req.getContextPath() + "/views/common/cart.jsp");
	}

	private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("cart") == null) { //tạo cart mới có 1 product duy nhất
			List<CartItemModel> cart = new ArrayList<CartItemModel>();
			int productId = Integer.parseInt(req.getParameter("productId"));
			cart.add(new CartItemModel(productService.find(productId), 1));
			session.setAttribute("cart", cart);
		} else { //ép kiểu
			List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute("cart");
			int productId = Integer.parseInt(req.getParameter("productId"));
			int productIndexInCart = isExisting(productId, cart);
			if (productIndexInCart == -1) {
				cart.add(new CartItemModel(productService.find(productId), 1));
			} else {
				ProductModel product = cart.get(productIndexInCart).getProduct();
				//lam service lay so luong hang trong kho
				int productStock = 50;
				int quantity = cart.get(productIndexInCart).getQuantity() + 1;
				if (quantity <= productStock)
				{
					cart.get(productIndexInCart).setQuantity(quantity);
				} else {
					req.setAttribute("outOfProduct", true);
					quantity -= 1;
					cart.get(productIndexInCart).setQuantity(quantity);
				}
				
			}
			session.setAttribute("cart", cart);
			req.setAttribute("cart", cart);
		}
		resp.sendRedirect(req.getContextPath() + "/views/common/cart.jsp");
	}

	private int isExisting(int productId, List<CartItemModel> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductId() == productId) {
				return i;
			}
		}
		return -1;
	}

	private void showCartItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/common/cart.jsp").forward(req, resp);
	}
}
