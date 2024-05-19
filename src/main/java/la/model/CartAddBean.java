package la.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import la.bean.Cart;
import la.bean.Item;
import la.dao.DAOException;
import la.dao.ItemDAO;

public class CartAddBean implements IBean {

	@Override
	public String execute(HttpServletRequest request) throws DAOException {
		//リクエストパラメータを取得
		int code = Integer.parseInt(request.getParameter("item_code"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//セッション・オブジェクトを取得
		HttpSession session = request.getSession(true);
		
		Cart cart = (Cart)session.getAttribute("cart");
		
		if (cart == null) { //セッション・オブジェクト内にカートがなければ作成
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		//ItemDAOクラスのインスタンスを生成
		ItemDAO dao = new ItemDAO();
		
		Item item = dao.findByPrimaryKey(code);
		
		cart.addCart(item, quantity);
		
		return "/cart.jsp";
	}

}
