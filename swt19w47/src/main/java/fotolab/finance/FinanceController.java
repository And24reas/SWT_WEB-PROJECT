package fotolab.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinanceController {

	@GetMapping("/finance")
	public String index() {
		return "finance";
	}

}
