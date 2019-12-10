package fotolab.user.customer;

import fotolab.user.UserManager;
import org.salespointframework.useraccount.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

	private final UserManager userManager;

	public CustomerController(UserManager userManager) {
		this.userManager = userManager;
	}


	@GetMapping("/users")
	public String customers(Model model) {
		model.addAttribute("customers", userManager.findAllByRole(Role.of("CUSTOMER")));
		return "customerList";
	}

}
