package la.bean;

import java.io.Serializable;

public class Item implements Serializable {
	//フィールド
	private int code;    //コード
	private String name; //商品名
	private int price;   //単価
	private int quantity;   //数量
	
	//コンストラクタ
	public Item() {

	}

	public Item(int code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public Item(int code, String name, int price, int quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
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
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
