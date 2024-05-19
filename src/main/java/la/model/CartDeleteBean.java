package la.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import la.bean.Cart;
import la.dao.DAOException;
import la.dao.ItemDAO;

public class CartDeleteBean implements IBean {

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
		int code = Integer.parseInt(request.getParameter("item_code"));
		
		//ItemDAOクラスのインスタンスを生成
		ItemDAO dao = new ItemDAO();
		
		cart.deleteCart(code);
		
		return "/cart.jsp";
	}

}
