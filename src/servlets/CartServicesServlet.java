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

import dao.CartDBHelper;
import model.Game;
import model.Order;
import services.ProcessOrder;

/**
 * Servlet implementation class CartServicesServlet
 * This servlet class is responsible for gamer's cart related operations
 * which helps the cart.jsp to communicate with necessary DBUtil classes.
 * 
 * @author IT19075754 Jayasinghe D.T.
 */
@WebServlet("/CartServicesServlet")
public class CartServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CartDBHelper cartDBHelper;

	@Resource(name="jdbc/dhscgamesdb")
	private DataSource dSource;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServicesServlet() {
		super();
	}

	//creates a cartDBHelper object when the servlet starts to execute
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			cartDBHelper = new CartDBHelper(dSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//receives a parameter called action and executes relevant methods using a switch case.
			//defaults action is set to getCart which returns the current cart of a user
			String action = request.getParameter("action");

			if(action == null) {
				action ="getCart";
			} 

			switch (action) {
			case "removeFromCart":
				removeFromCart(request,response);
				//go back to list of games in cart
				getCart(request,response);
				break;
			case "getCart":
				getCart(request,response);
				break;
			case "placeOrder":
				placeOrder(request,response);
				break;
			case "clearCart":
				clearCart(request,response);
				getCart(request,response);
				break;
			default:
				getCart(request,response);
			}

		}catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			request.getRequestDispatcher("/cart.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//sends order details to cartDBHelper to save a new order placed by the gamer
	private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int userid = Integer.parseInt(request.getParameter("userid"));
		List<Game> myCart = cartDBHelper.getCart();
		ProcessOrder processOrder = new ProcessOrder(userid, myCart);
		Order newOrder = processOrder.getOrder();
		
		int orderID = cartDBHelper.addNewOrder(newOrder, myCart);	
		newOrder.setoID(orderID);
		
		//clear cart
		cartDBHelper.clearCart(userid);
		
		request.setAttribute("NEWORDER", newOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/makepayment.jsp");
		dispatcher.forward(request, response);
	}

	//clears an existing cart of the current gamer logged in
	private void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		cartDBHelper.clearCart(userid);
	}

	//removes a game from the cart
	private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int gameid = Integer.parseInt(request.getParameter("gameid"));
		int userid = 2; //hard coded because there is no session
		cartDBHelper.removeGame(gameid, userid);
	}

	//returns all items in the gamer's cart
	private void getCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Game> cart = cartDBHelper.getCart();
		request.setAttribute("MYCART", cart);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
