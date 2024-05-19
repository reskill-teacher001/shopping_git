package la.model;

import javax.servlet.http.HttpServletRequest;

import la.dao.DAOException;

public class TopPageBean implements IBean {

	@Override
	public String execute(HttpServletRequest request) throws DAOException {
		return "/top.jsp";
	}

}
