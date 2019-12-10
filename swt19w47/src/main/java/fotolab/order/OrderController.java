package fotolab.order;

import fotolab.product.FotoProduct;
import fotolab.product.ProductForm;
import org.salespointframework.order.Cart;
import org.salespointframework.order.CartItem;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManager;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class OrderController {

	private Cart cart;

	private final OrderManager<Order> orderManager;

	public OrderController(OrderManager<Order> orderManager) {
		this.orderManager = orderManager;
	}

	@ModelAttribute("cart")
	Cart initializeCart() {
		cart = new Cart();
		return cart;
	}

	@GetMapping("/order")
	public String order(Model model) {
		return "order";
	}

	@GetMapping("/cart")
	ModelAndView basket(Model model, OrderForm form) {

		model.addAttribute("form", form);

		Cart cart = (Cart) model.getAttribute("cart");
		cart.forEach(form::addItem);

		return new ModelAndView("cart");
	}


	//Will be used when there is a catalog to add the items from
	@PostMapping("/cart")
	String addItem(@RequestParam("pid") FotoProduct p, @RequestParam("number") int number, @ModelAttribute Cart cart) {

		int amount = number <= 0 || number > 5 ? 1 : number;

		cart.addOrUpdateItem(p, Quantity.of(amount));

		return "redirect:/cart";
	}


	@PostMapping("/checkout")
	String buy(@ModelAttribute Cart cart, @LoggedIn Optional<UserAccount> userAccount) {


		//It's a prototype and we don't have users yet so this part is to be implemented

			return userAccount.map(account -> {

			var order = new Order(account, Cash.CASH);

			cart.addItemsTo(order);

			cart.clear();

			return "redirect:/";
		}).orElse("redirect:/cart");

	}

	@PostMapping("/emptyCart")
	String empty(@ModelAttribute Cart cart, @LoggedIn Optional<UserAccount> userAccount) {

		cart.clear();

		return "redirect:/cart";
	}


	@PostMapping("/removeItem/{id}")
	String delete(@ModelAttribute Cart cart,@RequestParam("productID") String id) {

		cart.removeItem(id);

		return "redirect:/cart";

	}

	@PostMapping("/increaseAmount/{id}")
	String increaseAmount(@ModelAttribute Cart cart, @RequestParam("productID") String id){

		CartItem cItem = cart.getItem(id).get();
		Quantity quantity = cItem.getQuantity();

		if(quantity.isLessThan(Quantity.of(5))) {
			cart.addOrUpdateItem(cItem.getProduct(), Quantity.of(1));
			return "redirect:/cart";
		}

		return "redirect:/cart";

	}


	@PostMapping("/decreaseAmount/{id}")
	String decreaseAmount(@ModelAttribute Cart cart, @RequestParam("productID") String id){

		CartItem cItem = cart.getItem(id).get();
		Quantity quantity = cItem.getQuantity();


		if(quantity.isGreaterThan(Quantity.of(1))) {

			cart.addOrUpdateItem(cItem.getProduct(), Quantity.of(-1));
			return "redirect:/cart";
		}
		return "redirect:/cart";
	}

}
