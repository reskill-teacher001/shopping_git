package la.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
	//フィールド
	private List<Item> items; //カテゴリ名
	
	//コンストラクタ
	public Cart() {
		items = new ArrayList<>();
	}
	
	//カートに商品を追加
	public void addCart(Item bean, int nums) {
		Item item = null;
		
		//カートに同じ商品が入っているかのチェック
		for (Item i : items) {
			if (i.getCode() == bean.getCode()) {
				item = i;
				break;
			}
		}
		
		if (item == null) { //カートに同じ商品がなかったら
			bean.setQuantity(nums);
			items.add(bean);
		} else { //カートに同じ商品があったら
			item.setQuantity(nums + item.getQuantity()); //現在の数量に加算
		}
	}

	//カートから商品を削除
	public void deleteCart(int itemCode) {
		//カートに指定のコードの商品が入っていたら削除
		for (Item item : items) {
			if (item.getCode() == itemCode) {
				items.remove(item);
				break;
			}
		}
	}

	//カート内の商品の取得
	public List<Item> getItems() {
		return items;
	}

	//カートに入っている商品の合計を取得
	public int getTotal() {
		int total = 0;
		
		for (Item item : items) {
			total += (item.getPrice() * item.getQuantity());
		}
		
		return total;
	}
}
