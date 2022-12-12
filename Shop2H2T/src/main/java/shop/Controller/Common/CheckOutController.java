package shop.Controller.Common;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.Models.ReceiptModel;
import shop.Services.Impl.CartServicesImpl;
import shop.Services.Impl.ReceiptServicesImpl;

@WebServlet(urlPatterns = {"/common/order/checkout"})
public class CheckOutController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	ReceiptServicesImpl receiptServicesImpl = new ReceiptServicesImpl();
	CartServicesImpl cartServicesImpl = new CartServicesImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int cartId = Integer.parseInt(req.getParameter("cartId"));
		java.util.Date utilDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("buyDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date buyDate = new Date(utilDate.getTime());
		ReceiptModel receiptToInsert = new ReceiptModel();
		receiptToInsert.setReleaseDate(buyDate);
		receiptToInsert.setCartId(cartId);
		
		receiptServicesImpl.insertReceipt(receiptToInsert);
		cartServicesImpl.changeStatusToCheckedOut(cartId);
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/common/receipt/list");
		rd.forward(req, resp);
	}
	
	

}
