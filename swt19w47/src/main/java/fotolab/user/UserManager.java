package fotolab.user;

import fotolab.authentification.RegistrationForm;
import org.salespointframework.useraccount.*;
import org.salespointframework.useraccount.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserManager {

	private static final Logger LOG = LoggerFactory.getLogger(UserManager.class);

	private final UserRepository userRepository;
	private final UserAccountManager userAccountManager;

	public UserManager(UserRepository userRepository, UserAccountManager userAccountManager) {
		this.userRepository = userRepository;
		this.userAccountManager = userAccountManager;
	}

	public User create(RegistrationForm registrationForm, Role role) {
		// create userAccount and persist it in database
		UserAccount userAccount = userAccountManager.create(registrationForm.getUserName(),
			Password.UnencryptedPassword.of(registrationForm.getPassword()), registrationForm.getMail(), role);
		// set additional values for userAccount
		userAccount.setFirstname(registrationForm.getFirstName());
		userAccount.setLastname(registrationForm.getLastName());
		// create User which connected is UserAccount
		User user = new User(userAccount, registrationForm.getAddress(), registrationForm.getPhone());
		// save User in separate repository (UserRepository)
		return this.userRepository.save(user);
	}

	public Streamable<User> findAll() {
		return userRepository.findAll();
	}

    public Streamable<User> findAllByRole(Role role) {
		Streamable<User> users = userRepository.findAll();

		return users.filter(user -> {
			LOG.info(user.getUserAccount().toString());
			return user.getUserAccount().hasRole(role);
		});
    }
}
