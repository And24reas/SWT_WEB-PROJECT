/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kickstart.controller;

import com.mysema.commons.lang.Assert;
import org.javamoney.moneta.Money;
import org.salespointframework.order.*;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kickstart.item.*;
import kickstart.order.*;

import static org.salespointframework.core.Currencies.EURO;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class WelcomeController {

	private Cart cart;

	@ModelAttribute("cart")
	Cart initializeCart() {
		cart = new Cart();
		return cart;
	}

	@RequestMapping("/")
	public String index() {
		return "welcome";
	}


	@PostMapping("/addItems")
	public String addItems(Model model, @ModelAttribute Cart cart) {

		System.out.println(cart.addOrUpdateItem(new Item("Frame", "image", Money.of(10, EURO), Item.ItemType.Hardware), 2));
		System.out.println(cart.addOrUpdateItem(new Item("Cleaning", "image1", Money.of(50, EURO), Item.ItemType.Hardware), 3));
		System.out.println(cart.addOrUpdateItem(new Item("Lens1", "image2", Money.of(30, EURO), Item.ItemType.Hardware), 4));
		System.out.println(cart.addOrUpdateItem(new Item("Sun Flare", "image3", Money.of(15, EURO), Item.ItemType.Hardware), 2));
		

		return "redirect:/";

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
	String addItem(@RequestParam("pid") Item item, @RequestParam("number") int number, @ModelAttribute Cart cart) {

		int amount = number <= 0 || number > 5 ? 1 : number;

		cart.addOrUpdateItem(item, Quantity.of(amount));

		switch (item.getType()) {
			case Hardware:
				return "redirect:hardware";
			default:
				return "redirect:reservation";
		}
	}


	@PostMapping("/checkout")
	String buy(@ModelAttribute Cart cart, @LoggedIn Optional<UserAccount> userAccount) {


		//It's a prototype and we don't have users yet so this part is to be implemented
		/*
			return userAccount.map(account -> {

			var order = new Order(account, Cash.CASH);

			cart.addItemsTo(order);

			cart.clear();

			return "redirect:/";
		}).orElse("redirect:/cart");
		 */
		cart.clear();

		return "redirect:/cart";

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

