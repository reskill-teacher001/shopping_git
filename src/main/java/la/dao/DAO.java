package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	//接続情報の設定１
	private static final String URL = "jdbc:postgresql:shopping_db"; //接続するDB名
	private static final String USER = "postgres"; //ユーザ名
	private static final String PASS = "postgres"; //パスワード
	
	protected Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		
		return con;
	}
}
