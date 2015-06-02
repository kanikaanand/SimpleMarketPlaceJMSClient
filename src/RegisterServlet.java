

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JMSClient jc = new JMSClient();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		if(request.getParameter("signup")!= null)
		{
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String username = request.getParameter("email");
			String password = request.getParameter("password");
			String userString="";
			String adString="";
			try {
				System.out.println("RegisterServlet : calling signup ");
				userString = jc.signUp(firstName,lastName,username,password);
				String []userTokens = userString.split(";");
				adString = jc.getUserAds(Integer.parseInt(userTokens[0]));
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			HttpSession session = request.getSession();
			session.setAttribute("user", userString);
			session.setAttribute("ads", adString);
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);

		}
	}

}
