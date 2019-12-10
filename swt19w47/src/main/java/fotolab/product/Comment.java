package fotolab.product;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENTS")
public class Comment implements Serializable {

	private static final long serialVersionUID = -7114101035786254953L;

	private @Id @GeneratedValue long id;

	private String text;
	private int rating;
	private LocalDateTime date;

	public Comment() {	}

	public Comment(String text, int rating, LocalDateTime date) {
		this.text = text;
		this.rating = rating;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public int getRating() {
		return rating;
	}

	public LocalDateTime getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return text;
	}

}
