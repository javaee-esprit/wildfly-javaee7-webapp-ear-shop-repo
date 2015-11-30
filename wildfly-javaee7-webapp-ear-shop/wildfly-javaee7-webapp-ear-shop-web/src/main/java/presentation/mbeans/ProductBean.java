package presentation.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;

import persistence.Category;
import persistence.Product;
import services.CatalogServiceLocal;

@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = -9107031652753540690L;

	@EJB
	private CatalogServiceLocal catalog;

	private Product product;
	private List<Product> products;
	private boolean formDisplayed;
	private List<Category> categories;
	private List<Product> filteredProducts;
	private List<SelectItem> categoriesFilterOptions;

	public ProductBean() {
	}

	@PostConstruct
	public void init() {
		product = new Product();
		products = catalog.findAllProducts();
		formDisplayed = false;
		categories = catalog.findAllCategories();
		categoriesFilterOptions = new ArrayList<SelectItem>(
				categories.size() + 1);
		categoriesFilterOptions.add(new SelectItem("", "select one"));
		for (Category category : categories) {
			categoriesFilterOptions.add(new SelectItem(category.getName(),
					category.getName()));
		}
	}

	public void doSave() {
		catalog.saveProduct(product);
		products = catalog.findAllProducts();
		formDisplayed = false;
	}

	public void doNew() {
		product = new Product();
		formDisplayed = true;
	}

	public void doDelete() {
		catalog.removeProduct(product);
		products = catalog.findAllProducts();
		formDisplayed = false;
	}

	public void onRowSelect() {
		formDisplayed = true;
	}

	public void onFilter() {
		formDisplayed = false;
	}

	public void doCancel() {
		formDisplayed = false;
	}

	public void handleFileUpload(FileUploadEvent event) {
		byte[] picture = event.getFile().getContents();
		product.setPicture(picture);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	public List<SelectItem> getCategoriesFilterOptions() {
		return categoriesFilterOptions;
	}

	public void setCategoriesFilterOptions(
			List<SelectItem> categoriesFilterOptions) {
		this.categoriesFilterOptions = categoriesFilterOptions;
	}

}
