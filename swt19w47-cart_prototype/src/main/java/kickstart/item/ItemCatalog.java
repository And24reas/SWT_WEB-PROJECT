
package kickstart.item;

import kickstart.item.Item.ItemType;
import org.salespointframework.catalog.Catalog;
import org.springframework.data.domain.Sort;


public interface ItemCatalog extends Catalog<Item> {

	static final Sort DEFAULT_SORT = Sort.by("productIdentifier").descending();

	Iterable<Item> findByType(ItemType type, Sort sort);
	default Iterable<Item> findByType(ItemType type) {
		return findByType(type, DEFAULT_SORT);
	}
}
