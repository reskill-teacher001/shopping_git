package la.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import la.bean.Cart;
import la.bean.Customer;
import la.dao.DAOException;
import la.dao.OrderDAO;

public class OrderCompleteBean implements IBean {

	@Override
	public String execute(HttpServletRequest request) throws DAOException {
		//セッション・オブジェクトを取得
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			throw new DAOException("セッションが切れています。もう一度トップページから操作してください。");
		}
		
		Cart cart = (Cart)session.getAttribute("cart");
		
		if (cart == null) { //セッション・オブジェクト内のカートの有無をチェック
			throw new DAOException("正しく操作してください。");
		}
		
		Customer customer = (Customer)session.getAttribute("customer");
		
		if (customer == null) { //セッション・オブジェクト内の顧客の有無をチェック
			throw new DAOException("正しく操作してください。");
		}
		
		//OrderDAOクラスのインスタンスを生成
		OrderDAO dao = new OrderDAO();
		
		int orderNumber = dao.saveOrder(customer, cart);
		
		session.removeAttribute("cart");
		session.removeAttribute("customer");
		
		request.setAttribute("orderNumber", orderNumber);
		
		return "/order.jsp";
	}

}
