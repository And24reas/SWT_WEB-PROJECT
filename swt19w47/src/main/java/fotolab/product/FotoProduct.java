package fotolab.product;

import org.salespointframework.catalog.Product;

import javax.money.MonetaryAmount;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FotoProduct extends Product {

	private String description;
	private String image;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	public FotoProduct(String name, MonetaryAmount price, String description, String image) {
		super(name, price);
		this.description = description;
		this.image = image;
	}

	public FotoProduct() {
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}
}
