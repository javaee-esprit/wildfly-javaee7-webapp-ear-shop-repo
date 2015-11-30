package presentation.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.Category;
import services.CatalogServiceLocal;

@ManagedBean
@ViewScoped
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = -9107031652753540690L;

	@EJB
	private CatalogServiceLocal catalog;

	private Category category;
	private List<Category> categories;
	private boolean formDisplayed;
	private List<Category> filteredCategories;

	public CategoryBean() {
	}

	@PostConstruct
	public void init() {
		category = new Category();
		categories = catalog.findAllCategories();
		formDisplayed = false;
	}

	public void doSave() {
		catalog.saveCategory(category);
		categories = catalog.findAllCategories();
		formDisplayed = false;
	}

	public void doNew() {
		category = new Category();
		formDisplayed = true;
	}

	public void doDelete() {
		catalog.removeCategory(category);
		categories = catalog.findAllCategories();
		formDisplayed = false;
	}

	public void doCancel() {
		formDisplayed = false;
	}
	
	public void onRowSelect() {
		formDisplayed = true;
	}
	
	public void onFilter(){
		category = new Category();
		formDisplayed = false;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public List<Category> getFilteredCategories() {
		return filteredCategories;
	}

	public void setFilteredCategories(List<Category> filteredCategories) {
		this.filteredCategories = filteredCategories;
	}

}
