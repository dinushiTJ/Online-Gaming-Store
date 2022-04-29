package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.PaymentDBHelper;
import model.Order;
import model.Payment;
import model.ShippingInfo;
import services.DatePicker;

/**
 * Servlet implementation class PaymentServicesServlet
 * This servlet class is responsible for gamer's payment related operations
 * which helps the makepayment.jsp to communicate with necessary DBUtil classes.
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

@WebServlet("/PaymentServicesServlet")
public class PaymentServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaymentDBHelper paymentDBHelper;

	@Resource(name="jdbc/dhscgamesdb")
	private DataSource dSource;

	
	//creates a paymentDBHelper class when the servlet is called
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			paymentDBHelper = new PaymentDBHelper(dSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServicesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//receives a parameter called action and executes relevant methods using a switch case.
			//defaults action is set to viewOrders which returns all ther orders of the current user to the order.jsp page
			String action = request.getParameter("action");
			
			if(action == null) {
				action ="viewOrders";
			} 

			switch (action) {
			case "viewOrders":
				viewOrders(request,response);
				break;
				
			case "savePayment":
				savePayment(request,response);
				break;
				
			case "updateInfo":
				updateShippingInfo(request,response);
				break;
				
			case "getShippingInfo":
				getInfo(request,response);
				break;
				
			case "deleteOrder":
				deleteOrder(request,response);
				break;
				
			case "newPayment":
				makeNewPayment(request,response);
				break;
				
			default:
				viewOrders(request,response);
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//sends new shipping info to the DB helper class to be updated in the db
	private void updateShippingInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderid"));
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String add1 = request.getParameter("addressline1");
		String add2 = request.getParameter("addressline2");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		
		ShippingInfo shipinfo = new ShippingInfo(orderId, fname, lname, add1, add2, country, state);
		paymentDBHelper.updateShippingInfo(shipinfo);

		viewOrders(request,response);
	}

	//retrives order details in order to display in makepayment jsp page
	private void makeNewPayment(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		Order order = paymentDBHelper.getOrder(orderid);
		
		request.setAttribute("NEWORDER", order);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/makepayment.jsp");
		dispatcher.forward(request, response);
	}

	//sends an order id to the helper class  of an order to be deleted
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		paymentDBHelper.deleteOrder(orderid);
		
		viewOrders(request,response);
	}

	//gets order info and shipping info related to a given order
	private void getInfo(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException, ServletException, IOException {
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		Order order = paymentDBHelper.getOrder(orderid);
		ShippingInfo shipinfo = paymentDBHelper.getShippingInfo(orderid);
		
		request.setAttribute("MYORDER", order);
		request.setAttribute("SHIPINFO", shipinfo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateshippinginfo.jsp");
		dispatcher.forward(request, response);
	}

	//sends new payment details to the helper class of an order to be saved in the database
	private void savePayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int orderId = Integer.parseInt(request.getParameter("orderid"));
		int gamerId = Integer.parseInt(request.getParameter("gamerid"));
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String add1 = request.getParameter("addressline1");
		String add2 = request.getParameter("addressline2");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String pMethod = request.getParameter("paymentMethod");
		String ccname = request.getParameter("ccname");
		String ccnum = request.getParameter("ccnumber");
		String ccexp = request.getParameter("ccexpiration");
		String cvv = request.getParameter("cccvv");
		
		DatePicker dp = new DatePicker();
		String paymentDate = dp.getCurrentDate();
		
		ShippingInfo shipinfo = new ShippingInfo(orderId, fname, lname, add1, add2, country, state);
		Payment payment = new Payment(gamerId, orderId, pMethod, ccname, ccnum, ccexp, cvv, paymentDate);
		paymentDBHelper.addPaymentDetails(shipinfo, payment);

		viewOrders(request,response);
	}

	//requests helper class for all order details to be fetched and sends them to the orders.jsp
	private void viewOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Order> allOrders = paymentDBHelper.getAllOrders();
		request.setAttribute("ALLORDERS", allOrders);
		RequestDispatcher dispatcherGETALLGAMES = request.getRequestDispatcher("/orders.jsp");
		dispatcherGETALLGAMES.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
