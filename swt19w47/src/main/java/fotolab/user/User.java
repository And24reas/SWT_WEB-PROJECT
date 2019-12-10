package fotolab.user;

import org.salespointframework.useraccount.UserAccount;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class User {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private UserAccount userAccount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private UserAddress userAddress;

	@Column
	private String phoneNumber;


	public User(UserAccount userAccount, UserAddress userAddress, String phoneNumber) {
		this.userAddress = userAddress;
		this.userAccount = userAccount;
		this.phoneNumber = phoneNumber != null ? phoneNumber : "-";
	}

	public User() {}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

}
