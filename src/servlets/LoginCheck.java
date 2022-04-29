package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String un=request.getParameter("loginuname");
		String pw=request.getParameter("loginpwd");

		//Connect to mysql and verify username password

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// loads driver
			//Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinegamingsite?characterEncoding=latin1&useConfigs=maxPerformance", "root", "P@55w0rd"); // gets a new connection
			
			//connection changed to a different db for testing purposes
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dhscgamesdb?characterEncoding=latin1&useConfigs=maxPerformance", "root", "");

			//table changed for testing purposes
			PreparedStatement ps2 = c.prepareStatement("select username, password from registereduser where username = ? and password =  ?");
			ps2.setString(1, un);
			ps2.setString(2, pw);

			ResultSet rs2 = ps2.executeQuery();

			if (rs2.next() == true) 
			{ 
				HttpSession session = request.getSession();
				String username = rs2.getString("username");
				session.setAttribute("userName", username);
				response.sendRedirect("gamerHome.jsp");
				return;
			}
			else 
			{
				String destination = "invalidLogin.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);

				String invalidLoginMessage = "Invalid Login Credentials. Re-enter login credentials.";
				request.setAttribute("invalidLoginMessage", invalidLoginMessage);
				requestDispatcher.forward(request, response);

				response.sendRedirect("invalidLogin.jsp");
				return;
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}