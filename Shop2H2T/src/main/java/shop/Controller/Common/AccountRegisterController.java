package shop.Controller.Common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.Services.Impl.AccountServicesImpl;

@WebServlet(urlPatterns = {"/common/register"})
public class AccountRegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	AccountServicesImpl accountService = new AccountServicesImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmedPassword = req.getParameter("confirmedPassword");
		if(password.equals(confirmedPassword))
		{
			accountService.registerAccount(username, confirmedPassword);
			resp.sendRedirect(req.getContextPath() + "/views/common/login.jsp");			
		}
		else
		{
			resp.sendRedirect(req.getContextPath() + "/views/common/register.jsp");
		}
	}
	

}
