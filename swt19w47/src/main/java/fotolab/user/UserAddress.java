package fotolab.user;

import javax.persistence.*;

@Entity
@Table
public class UserAddress {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String streetName;

	@Column
	private String houseNumber;

	@Column
	private int postCode;

	@Column
	private String city;

	@Column
	private String country;

	public UserAddress(String streetName, String houseNumber, int postCode, String city, String country) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
		this.country = country;
	}

	public UserAddress() {
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public String getStreetString() {
		return streetName + " " + houseNumber;
	}
}
