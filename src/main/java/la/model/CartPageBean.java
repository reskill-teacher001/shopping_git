package la.model;

import javax.servlet.http.HttpServletRequest;

import la.dao.DAOException;

public class CartPageBean implements IBean {

	@Override
	public String execute(HttpServletRequest request) throws DAOException {
		return "/cart.jsp";
	}

}
