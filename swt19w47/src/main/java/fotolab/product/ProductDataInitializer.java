package fotolab.product;

import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.salespointframework.core.Currencies.EURO;

@Component
@Order(20)
public class ProductDataInitializer implements DataInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(ProductDataInitializer.class);

	private final ProductManager productManager;

	public ProductDataInitializer(ProductManager productManager) {
		this.productManager = productManager;
	}

	@Override
	public void initialize() {

		if (productManager.findAll().iterator().hasNext()) {
			return;
		}

		LOG.info("Creating default products.");

		String imageUrl = "placeholder";

		List.of(
				new ProductForm("product 1", Money.of(10, EURO), "cooles Produkt", "HARDWARE", imageUrl),
				new ProductForm("product 2", Money.of(10, EURO), "cooles Produkt", "HARDWARE", imageUrl),
				new ProductForm("product 3", Money.of(10, EURO), "cooles Produkt", "HARDWARE", imageUrl),
				new ProductForm("product 4", Money.of(10, EURO), "cooles Produkt", "SERVICE", imageUrl),
				new ProductForm("product 5", Money.of(10, EURO), "cooles Produkt", "SERVICE", imageUrl),
				new ProductForm("product 6", Money.of(10, EURO), "cooles Produkt", "SERVICE", imageUrl),
				new ProductForm("product 7", Money.of(10, EURO), "cooles Produkt", "SERVICE", imageUrl)
		).forEach(productManager::createOrUpdate);

		productManager.findAll().forEach(fotoProduct -> fotoProduct.addComment(new Comment("comment", 3, LocalDateTime.now())));

	}
}
