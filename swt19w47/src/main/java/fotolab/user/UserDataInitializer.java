package fotolab.user;

import fotolab.authentification.RegistrationForm;
import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
@Order(10)
public class UserDataInitializer implements DataInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(UserDataInitializer.class);

	private final UserAccountManager userAccountManager;
	private final UserManager userManager;

	/**
	 * Creates a new {@link UserDataInitializer} with the given {@link UserAccountManager} and
	 * {@link UserDataInitializer}.
	 *
	 * @param userAccountManager must not be {@literal null}.
	 * @param userManager must not be {@literal null}.
	 */
	UserDataInitializer(UserAccountManager userAccountManager, UserManager userManager) {

		Assert.notNull(userAccountManager, "UserAccountManager must not be null!");
		Assert.notNull(userManager, "CustomerRepository must not be null!");

		this.userAccountManager = userAccountManager;
		this.userManager = userManager;
	}

	/*
	 * (non-Javadoc)
	 * @see org.salespointframework.core.DataInitializer#initialize()
	 */
	@Override
	public void initialize() {

		// Skip creation if database was already populated
		if (userAccountManager.findByUsername("admin").isPresent()) {
			return;
		}

		LOG.info("Creating default users.");

		userManager.create(new RegistrationForm("admin", "firstName", "lastName",
			"mail", "123", "streetName", "houseNumber", 10000,
			"city", "country","01489475184"), Role.of("ADMIN"));

		String password = "sinep";

		List.of(
			new RegistrationForm("customer1", "firstName", "lastName", "mail1", password,
				"streetName", "houseNumber", 10000, "city", "country", "01489475184"),
			new RegistrationForm("customer2", "firstName", "lastName", "mail2", password,
				"streetName", "houseNumber", 10000, "city", "country", "01489475184"),
			new RegistrationForm("customer3", "firstName", "lastName", "mail3", password,
				"streetName", "houseNumber", 10000, "city", "country", "01489475184"),
			new RegistrationForm("customer4", "firstName", "lastName", "mail4", password,
				"streetName", "houseNumber", 10000, "city", "country", "01489475184"),
			new RegistrationForm("customer5", "firstName", "lastName", "mail5", password,
				"streetName", "houseNumber", 10000, "city", "country", "01489475184"),
			new RegistrationForm("customer6", "firstName", "lastName", "mail6", password,
				"streetName", "houseNumber", 10000, "city", "country", "01489475184")
		).forEach((registrationForm -> userManager.create(registrationForm, Role.of("CUSTOMER"))));

		List.of(
				new RegistrationForm("employee1", "firstName", "lastName", "mail7", password,
						"streetName", "houseNumber", 10000, "city", "country", "01489475184"),
				new RegistrationForm("employee2", "firstName", "lastName", "mail8", password,
						"streetName", "houseNumber", 10000, "city", "country", "01489475184"),
				new RegistrationForm("employee3", "firstName", "lastName", "mail9", password,
						"streetName", "houseNumber", 10000, "city", "country", "01489475184")
		).forEach((registrationForm -> userManager.create(registrationForm, Role.of("EMPLOYEE"))));

	}

}
