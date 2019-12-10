package fotolab.product;

import org.salespointframework.catalog.Catalog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductManager {

	private final Catalog<FotoProduct> catalog;

	public ProductManager(Catalog<FotoProduct> catalog) {
		this.catalog = catalog;
	}

	public FotoProduct createOrUpdate(ProductForm productForm) {
		FotoProduct fotoProduct = new FotoProduct(productForm.getName(), productForm.getPrice(),
				productForm.getDescription(), productForm.getImage());
		fotoProduct.addCategory(productForm.getProductType());
		this.catalog.save(fotoProduct);
		return fotoProduct;
	}

	public Iterable<FotoProduct> findAll() {
		return catalog.findAll();
	}


	public void remove(FotoProduct product) {
		catalog.delete(product);
	}

	public Iterable<FotoProduct> findAllByType(String productType) {
		return catalog.findByAnyCategory(productType);
	}
}
