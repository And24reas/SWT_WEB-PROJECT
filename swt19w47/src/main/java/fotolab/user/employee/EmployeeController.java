package fotolab.user.employee;

import fotolab.user.UserManager;
import org.salespointframework.useraccount.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

	private final UserManager userManager;

	public EmployeeController(UserManager userManager) {
		this.userManager = userManager;
	}


	@GetMapping("/employees")
	public String employees(Model model) {
		model.addAttribute("employees", userManager.findAllByRole(Role.of("EMPLOYEE")));
		return "employeeList";
	}

}
