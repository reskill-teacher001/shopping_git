package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import la.bean.Cart;
import la.bean.Customer;
import la.bean.Item;

public class OrderDAO extends DAO {
	
	//注文保存メソッド
	public int saveOrder(Customer customer, Cart cart) throws DAOException {
		int orderNumber = 0;
		
		try (
			//正常にDBに接続された時に利用できるリモコンcon
			//Connection con = DriverManager.getConnection(url, user, pass);
			Connection con = getConnect();
		) {
			con.setAutoCommit(false); //トランザクション開始
			
			String sql1 = "SELECT nextval('customer_code_seq') AS cust_code";
			
			//SQL文を実行する準備をする
			PreparedStatement ps = con.prepareStatement(sql1);
			
			//SQLを実行して結果を取得する
			ResultSet rs = ps.executeQuery();

			int custCode = 0;
			
			if (rs.next() == true) { //レコードがあったら
				//レコードの列のデータを取得する
				custCode = rs.getInt("cust_code"); //シーケンスを取得
			}
			
			String sql2 = "SELECT nextval('ordered_code_seq') AS order_code";
			
			//SQL文を実行する準備をする
			ps = con.prepareStatement(sql2);
			
			//SQLを実行して結果を取得する
			rs = ps.executeQuery();

			if (rs.next() == true) { //レコードがあったら
				//レコードの列のデータを取得する
				orderNumber = rs.getInt("order_code"); //シーケンスを取得
			}
			
			//登録する顧客の情報を取得
			String name = customer.getName();
			String address = customer.getAddress();
			String tel = customer.getTel();
			String email = customer.getEmail();
			
			String sql3 = "INSERT INTO customer(code, name, address, tel, email) VALUES(?, ?, ?, ?, ?)";

			//SQL文を実行する準備をする
			ps = con.prepareStatement(sql3);
			ps.setInt(1, custCode);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, tel);
			ps.setString(5, email);

			//SQLを実行して結果を取得する
			int rows1 = ps.executeUpdate();
			
			String sql4 = "INSERT INTO ordered(code, customer_code, ordered_date, total_price) VALUES(?, ?, ?, ?)";

			//SQL文を実行する準備をする
			ps = con.prepareStatement(sql4);
			ps.setInt(1, orderNumber);
			ps.setInt(2, custCode);
			ps.setDate(3, new Date(System.currentTimeMillis()));
			ps.setInt(4, cart.getTotal());
			
			//SQLを実行して結果を取得する
			int rows2 = ps.executeUpdate();
			
			int rows3 = 0;
			
			String sql5 = "INSERT INTO ordered_detail(ordered_code, item_code, num) VALUES(?, ?, ?)";

			//SQL文を実行する準備をする
			List<Item> items = cart.getItems();
			
			ps = con.prepareStatement(sql5);
			
			for (Item item : items) {
				ps.setInt(1, orderNumber);
				ps.setInt(2, item.getCode());
				ps.setInt(3, item.getQuantity());
				
				int rows = ps.executeUpdate();
				rows3++;
			}
			
			if (rows1 == 1 && rows2 == 1 && rows3 > 0) {
				con.commit();
			} else {
				con.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}

		return orderNumber;
	}
	
	//削除メソッド
	public int deleteByPrimaryKey(int key) throws DAOException {
		int rows = 0;

		String sql = "DELETE FROM item WHERE code = ?";

		try (
			//正常にDBに接続された時に利用できるリモコンcon
			//Connection con = DriverManager.getConnection(url, user, pass);
			Connection con = getConnect();
		) {
			//SQL文を実行する準備をする
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, key);

			//SQLを実行して結果を取得する
			rows = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}

		return rows;
	}

}
