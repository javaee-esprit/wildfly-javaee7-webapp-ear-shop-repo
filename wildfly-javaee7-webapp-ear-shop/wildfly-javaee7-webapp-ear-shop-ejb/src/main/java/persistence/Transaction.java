package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	private TransactionPK pk;
	private Integer quantity;
	private Customer customer;
	private Product product;

	public Transaction() {
	}
	
	

	public Transaction(Customer customer, Product product, Date date, Integer quantity) {
		this.getPk().setCustomerId(customer.getId());
		this.getPk().setProductId(product.getId());
		this.getPk().setDate(date);
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}



	@EmbeddedId
	public TransactionPK getPk() {
		if (pk == null) {
			pk = new TransactionPK();
		}
		return pk;
	}

	public void setPk(TransactionPK pk) {
		this.pk = pk;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@ManyToOne
	@JoinColumn(name = "customer_fk", insertable = false, updatable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	@JoinColumn(name = "product_fk", insertable = false, updatable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
