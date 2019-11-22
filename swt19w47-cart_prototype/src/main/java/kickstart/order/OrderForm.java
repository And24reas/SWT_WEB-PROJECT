package kickstart.order;

import org.salespointframework.order.CartItem;

import java.util.ArrayList;
import java.util.List;

public class OrderForm {

	private List<CartItem> items;

	public OrderForm() {
		this.items = new ArrayList<>();
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void ListItems(List<CartItem> items) {
		this.items = items;
	}

	public CartItem addItem(CartItem item) {
		items.add(item);
		return item;
	}

	public CartItem removeItem(CartItem item) {
		items.remove(item);
		return item;
	}

	@Override
	public String toString() {
		return items.toString();
	}
}
