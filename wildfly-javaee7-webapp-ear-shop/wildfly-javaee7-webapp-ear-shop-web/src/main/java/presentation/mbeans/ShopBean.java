package presentation.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.Product;
import services.CatalogServiceLocal;

@ManagedBean
@ViewScoped
public class ShopBean implements Serializable {

	private static final long serialVersionUID = -5627014334361779350L;

	@EJB
	private CatalogServiceLocal catalog;

	private List<Product> products;

	public ShopBean() {
	}

	@PostConstruct
	public void init() {
		products = catalog.findAllProducts();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
