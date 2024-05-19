package la.model;

import javax.servlet.http.HttpServletRequest;

import la.dao.DAOException;

public interface IBean {
	public abstract String execute(HttpServletRequest request) throws DAOException;
}
