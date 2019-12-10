package fotolab.authentification;

import fotolab.user.UserAddress;

import javax.validation.constraints.NotEmpty;

public class RegistrationForm {

	@NotEmpty(message = "{RegistrationForm.userName.NotEmpty}") //
	private final String userName;

	@NotEmpty(message = "{RegistrationForm.firstName.NotEmpty}") //
	private final String firstName;

	@NotEmpty(message = "{RegistrationForm.LastName.NotEmpty}") //
	private final String lastName;

	@NotEmpty(message = "{RegistrationForm.mail.NotEmpty}") //
	private final String mail;

	@NotEmpty(message = "{RegistrationForm.password.NotEmpty}") //
	private final String password;

	@NotEmpty(message = "{RegistrationForm.streetName.NotEmpty}") //
	private final String streetName;

	@NotEmpty(message = "{RegistrationForm.houseNumber.NotEmpty}") //
	private final String houseNumber;

	@NotEmpty(message = "{RegistrationForm.postCode.NotEmpty}") //
	private final int postCode;

	@NotEmpty(message = "{RegistrationForm.city.NotEmpty}") //
	private final String city;

	@NotEmpty(message = "{RegistrationForm.country.NotEmpty}") //
	private final String country;

	@NotEmpty(message = "{RegistrationForm.phone.NotEmpty}") //
	private final String phone;

	public RegistrationForm(String userName, String firstName, String lastName, String mail, String password,
						String streetName, String houseNumber, int postCode, String city, String country,
						String phone) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
		this.country = country;
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public UserAddress getAddress() {
		return new UserAddress(streetName, houseNumber, postCode, city, country);
	}

}
