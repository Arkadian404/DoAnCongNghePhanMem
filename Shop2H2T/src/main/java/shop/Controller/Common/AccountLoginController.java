package shop.Controller.Common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.Services.Impl.AccountServicesImpl;
import shop.Services.Impl.SellerServicesImpl;
import shop.Services.Impl.StoreServicesImpl;

@WebServlet(urlPatterns = {"/common/login"})
public class AccountLoginController extends HttpServlet{

	AccountServicesImpl accountService = new AccountServicesImpl();
	SellerServicesImpl sellerService = new SellerServicesImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean loginSuccessful = accountService.authenticateAccount(username, password);
		if(loginSuccessful) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);				
			String userRole = (String)accountService.findRoleAccount(username, password);
			session.setAttribute("userRole", userRole);
			if(userRole.equalsIgnoreCase("seller"))
			{
				int sellerId = accountService.findSellerId(username);
				session.setAttribute("sellerId", sellerId);
				session.setAttribute("storeId", sellerService.findStoreId(sellerId));
			}
			//nếu user role là seller session đưa sellerId vào
			resp.sendRedirect(req.getContextPath() + "/common/home");
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/views/common/login.jsp");
		}	
	}

	private static final long serialVersionUID = 1L;

}
