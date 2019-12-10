package fotolab.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class ProductController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	private ProductManager productManager;

	public ProductController(ProductManager productManager) {
		this.productManager = productManager;
	}

	@GetMapping("/products")
	public String products(@RequestParam Optional<String> filterType, @RequestParam Optional<String> search,
						   Model model) {
		// get all products
		Iterable<FotoProduct> fotoProducts = productManager.findAll();
		if (filterType.isPresent() && !filterType.get().equals("ALL")) {
			// if there is a filterType which is not 'ALL' in the routParam then we filter the products
			// can be: 'HARDWARE' or 'SERVICE'
			fotoProducts = productManager.findAllByType(filterType.get());
			model.addAttribute("type", filterType.get());
		} else {
			// if there is no other filterType set, we just use the 'ALL' filter
			model.addAttribute("type", "ALL");
		}
		// we check if there additionally is a search text with whom we should filter the products
		if (search.isPresent() && !search.get().isEmpty() && !search.get().isBlank()) {
			// if there is a search text (which isn't blank/empty) we search for the products which contain the given
			// String, we then add the filtered products to the model (instead of the whole products)
			model.addAttribute("products", filterProductsWithSearch(search.get(), fotoProducts));
		} else {
			// if there is no search String we just set the model attribute as the products
			model.addAttribute("products", fotoProducts);
		}
		return "productList";
	}

	private List<FotoProduct> filterProductsWithSearch(String search, Iterable<FotoProduct> fotoProducts) {
		return StreamSupport.stream(fotoProducts.spliterator(), false)
			.filter(fotoProduct -> {
				return fotoProduct.getName().contains(search);
			})
			.collect(Collectors.toList());
	}

	@GetMapping("/addProduct")
	public String addProduct(Model model, ProductForm form) {
		model.addAttribute("form", form);
		return "addProduct";
	}

	@PostMapping("/deleteProduct/{product}")
	public String deleteProduct(@PathVariable FotoProduct product) {
		productManager.remove(product);
		return "redirect:/products";
	}

	@PostMapping("/addProduct")
	public String addProductPost(@Valid ProductForm form, Errors result) {
		FotoProduct product = productManager.createOrUpdate(form);
		return "redirect:/editProduct/" + product.getId().getIdentifier();
	}

	@GetMapping("/editProduct/{product}")
	public String editProduct(Model model, ProductForm form, @PathVariable FotoProduct product) {
		model.addAttribute("form", form);
		model.addAttribute("product", product);
		return "editProduct";
	}
}
