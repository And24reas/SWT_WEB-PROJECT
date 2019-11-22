package kickstart.item;

import org.javamoney.moneta.Money;
import org.salespointframework.catalog.Product;

import javax.persistence.Entity;

@Entity
public class Item extends Product {

	public static enum ItemType {
		Hardware, Reservation;
	}

	private String name, image;
	private ItemType type;

	@SuppressWarnings("unused")
	private Item() {}

	public Item(String name, String image, Money price, ItemType type) {

		super(name, price);
		this.image = image;
		this.type = type;
	}

	

	public void setImage(String image) {
		this.image = image;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public ItemType getType() {
		return type;
	}

	public String getImage() {
		return image;
	}
}
