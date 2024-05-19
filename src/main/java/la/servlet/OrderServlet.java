package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.model.IBean;
import la.model.OrderCompleteBean;
import la.model.OrderConfirmBean;
import la.model.OrderPageBean;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータを取得
		String action = request.getParameter("action");
		
		IBean bean = null;
		String page = "/errInternal.jsp";
		
		try {
			if (action == null || action.equals("") || action.equals("input_customer")) {
				bean = new OrderPageBean();
			} else if (action.equals("confirm")) {
				bean = new OrderConfirmBean();
			} else if (action.equals("order")) {
				bean = new OrderCompleteBean();
			}
			
			page = bean.execute(request);
		} catch (Exception e) {
			request.setAttribute("message", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
