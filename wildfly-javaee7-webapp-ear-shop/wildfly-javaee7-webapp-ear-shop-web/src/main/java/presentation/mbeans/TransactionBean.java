package presentation.mbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import persistence.Customer;
import persistence.Product;
import persistence.User;
import services.TransactionServiceLocal;

@ManagedBean
@ViewScoped
public class TransactionBean {

	@ManagedProperty("#{authBean.user}")
	private User authUser;

	@EJB
	private TransactionServiceLocal transactionServiceLocal;

	private List<Map<String, Object>> purchases;
	private List<Product> products;

	public TransactionBean() {
	}

	@PostConstruct
	public void init() {
		purchases = transactionServiceLocal
				.purchasesByCustomer((Customer) authUser);
		products = transactionServiceLocal.findProductsByCustomer(authUser.getId());
	}

	public void setAuthUser(User authUser) {
		this.authUser = authUser;
	}

	public List<Map<String, Object>> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Map<String, Object>> purchases) {
		this.purchases = purchases;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
