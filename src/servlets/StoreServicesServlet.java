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

import dao.StoreDBHelper;
import model.Game;

/**
 * Servlet implementation class StoreServicesServlet
 * This servlet class is responsible for game store related operations
 * which helps the store.jsp to communicate with necessary DBUtil classes.
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

@WebServlet("/StoreServicesServlet")
public class StoreServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StoreDBHelper storeDBHelper;

	@Resource(name="jdbc/dhscgamesdb")
	private DataSource dSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoreServicesServlet() {
		super();
	}

	//creates storeDBHelper class when the servlet is called
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			storeDBHelper = new StoreDBHelper(dSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//receives a parameter called action and executes relevent methods using a switch case.
			//defaults action is set to getAllGames which returns the all games in the store to the store.jsp page
			String action = request.getParameter("action");
			
			if(action == null) {
				action ="getAllGames";
			} 

			switch (action) {
			case "addToCart":
				addToCart(request,response);
				getAllGames(request,response);
				break;
				
			case "getAllGames":
				getAllGames(request,response);
				break;
				
			default:
				getAllGames(request,response);
				break;
			}	
		}catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			try {
				getAllGames(request,response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//fetches all games with the help of the DB Helper class
	private void getAllGames(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Game> allGames = storeDBHelper.getAllGames();
		request.setAttribute("ALLGAMES", allGames);
		RequestDispatcher dispatcherGETALLGAMES = request.getRequestDispatcher("/store.jsp");
		dispatcherGETALLGAMES.forward(request, response);
	}

	//sends a game to the DB helper class to add to the cart of the gamer
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String gameid = request.getParameter("gameid");
		int userid = 2;
		storeDBHelper.addToCart(gameid, userid);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
