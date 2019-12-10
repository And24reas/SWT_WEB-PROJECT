package fotolab.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user/editUser")
	public String editUser(Model model) {
		return "editUser";
	}

}
