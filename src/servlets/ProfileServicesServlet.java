package servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.ProfileDBHelper;
import model.GamerProfile;


/**
 * Servlet implementation class ProfileServicesServlet
 * This servlet class is responsible for gamer's profile related operations
 * which helps the profile.jsp to communicate with necessary DBUtil classes.
 * 
 * @author IT19075754 Jayasinghe D.T.
 */
@WebServlet("/ProfileServicesServlet")
public class ProfileServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDBHelper profileDBHelper;
	

	@Resource(name="jdbc/dhscgamesdb")
	private DataSource dSource;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServicesServlet() {
		super();
	}

	@Override
	//creates a DB helper object when the servlet is created
	public void init() throws ServletException {
		super.init();
		try {
			profileDBHelper = new ProfileDBHelper(dSource);
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
			//defaults action is set to getProfile which redirects to the profile page of the gamer
			String action = request.getParameter("action");

			if(action == null) {
				action ="getProfile";
			} 

			switch (action) {
			case "getProfile":
				getProfile(request,response);
				break;
			case "setNewUserDetails":
				setNewUserDetails(request,response);
				break;
			default:
				getProfile(request,response);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//sends new profile details to be updated to the db helper class
	private void setNewUserDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("userName");
		int userID = Integer.parseInt(request.getParameter("userid"));
		String newUserName = request.getParameter("newusername");
		String newPassword = request.getParameter("newpassword");
		profileDBHelper.setNewUserDetails(userID, newUserName, newPassword);
		
		session.setAttribute("userName", newUserName);
		getProfile(request,response);
	}

	//gets profile details of the currently logged in gamer
	private void getProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("userName");
		GamerProfile gamer = profileDBHelper.getProfile(username);
		request.setAttribute("gamer", gamer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}