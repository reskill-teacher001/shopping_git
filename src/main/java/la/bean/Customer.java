package la.bean;

import java.io.Serializable;

public class Customer implements Serializable {
	//フィールド
	private int code;       //コード
	private String name;    //名前
	private String address; //住所
	private String tel;     //電話番号
	private String email;   //Eメール
	
	//コンストラクタ
	public Customer() {

	}

	public Customer(String name, String address, String tel, String email) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
	}

	public Customer(int code, String name, String address, String tel, String email) {
		this.code = code;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
	}

	//セッタ＆ゲッタ
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
