package la.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import la.bean.Cart;
import la.bean.Customer;
import la.dao.DAOException;

public class OrderConfirmBean implements IBean {

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
		
		//リクエストパラメータを取得
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		Customer customer = new Customer(name, address, tel,email);
		
		session.setAttribute("customer", customer);
		
		return "/confirm.jsp";
	}

}
