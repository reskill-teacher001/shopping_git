package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.Category;
import la.dao.ItemDAO;
import la.model.IBean;
import la.model.ItemListBean;
import la.model.TopPageBean;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
        try {
            // カテゴリ一覧は最初にアプリケーションスコープへ入れる
            ItemDAO dao = new ItemDAO();
            List<Category> list = dao.findAllCategory();
            
            ServletContext application = getServletContext();
            application.setAttribute("categories", list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException();
        }
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
			if (action == null || action.equals("") || action.equals("top")) {
				bean = new TopPageBean();
			} else if (action.equals("list")) {
				bean = new ItemListBean();
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
