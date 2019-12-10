package fotolab.product;

import javax.money.MonetaryAmount;
import java.awt.*;

public class ProductForm {

	private String name;
	private MonetaryAmount price;
	private String description;
	private String productType;
	private String image;

	public ProductForm(String name, MonetaryAmount price, String description, String productType, String image) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public String getProductType() {
		return productType;
	}
}
