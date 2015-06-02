import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JMSClient jc = new JMSClient();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in servlet");
		//HttpSession session = request.getSession();
		try
		{
			if(request.getParameter("signin")!=null)
			{
				System.out.println("In login serv");
				String username = request.getParameter("uname");
				String password = request.getParameter("password");
				String userString  = jc.signIn(username,password);
				String []userTokens = userString.split(";");
				String adString = jc.getUserAds(Integer.parseInt(userTokens[0]));
				System.out.println(" called getUserAds() : BACK in LoginServlet");
				HttpSession session = request.getSession();
				session.setAttribute("user", userString);
				session.setAttribute("ads", adString);
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}
			else if(request.getParameter("newuser")!=null)
			{

				RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);

			}

		}
		catch(JMSException j)
		{

		}
	}

}
