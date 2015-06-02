import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NewItemServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JMSClient jc = new JMSClient();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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
		if(request.getParameter("postad")!=null)
		{
			System.out.println(" in Home servlet: post ad");
			HttpSession session = request.getSession();
			String userString =(String) session.getAttribute("user");
			String []userTokens = userString.split(";");
			int userId = Integer.parseInt(userTokens[0]);
			String itemName = request.getParameter("itemname");
			String itemDesc = request.getParameter("itemdesc");
			String sellerInfo = request.getParameter("sellerinfo");
			String itemPrice  = request.getParameter("itemprice");
			try {
				jc.postAd(userId, itemName, itemDesc, sellerInfo, itemPrice);
			} catch (JMSException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);
		}
		else if(request.getParameter("subscribe")!=null)
		{
			System.out.println(" in Home servlet: subscribe");
			HttpSession session = request.getSession();
			String userString =(String) session.getAttribute("user");
			String []userTokens = userString.split(";");
			int userId = Integer.parseInt(userTokens[0]);
			try {
				jc.subscribeUser(userId);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);
			

		}
		else if(request.getParameter("unsubscribe")!=null)
		{
			System.out.println(" in Home servlet: unsubscribe");
			HttpSession session = request.getSession();
			String userString =(String) session.getAttribute("user");
			String []userTokens = userString.split(";");
			int userId = Integer.parseInt(userTokens[0]);
			try {
				jc.unsubscribeUser(userId);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);
		}
	}

}