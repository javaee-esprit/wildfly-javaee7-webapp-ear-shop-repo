package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Customer;
import persistence.Product;
import persistence.Transaction;

@Stateless
public class TransactionService implements TransactionServiceRemote, TransactionServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
    public TransactionService() {
    }

	public Boolean createTransaction(Integer customerId, Integer productId,
			Date date, Integer quantity) {
		Boolean success = false;
		Customer customer = em.find(Customer.class, customerId);
		Product product = em.find(Product.class, productId);
		if (product.getStock() >= quantity) {
			Transaction transaction = new Transaction(customer,product,date,quantity);
			product.setStock(product.getStock() - quantity);
			em.persist(transaction);
			success = true;
		}
		return success;
	}

	public List<Product> findProductsByCustomer(Integer customerId) {
		return em
				.createQuery("select distinct p from Product p join p.transactions t where t.customer.id=:x", Product.class)
				.setParameter("x", customerId)
				.getResultList();
	}
	
	public List<Map<String, Object>> purchasesByCustomer(
			Customer customer) {
		List<Object[]> results =
		em
		.createQuery("select p.name,p.unitCost,t.pk.date,t.quantity  from Product p join p.transactions t where t.customer=:x", Object[].class)
		.setParameter("x", customer)
		.getResultList();
		List<Map<String, Object>> purchases = new ArrayList<>();
		for (Object[] result: results) {
			Map<String, Object> purchase = new HashMap<>();
			purchase.put("productName", result[0]);
			purchase.put("productUnitCost", result[1]);
			purchase.put("date", result[2]);
			purchase.put("quantity", result[3]);
			purchases.add(purchase);
		}
		return purchases;
	}
	
	public Map<String, Long> sales() {
		List<Object[]> results = em
				.createQuery("select p.name, count(t.quantity) from Product p join p.transactions t group by p.name", Object[].class)
				.getResultList();
		Map<String, Long> histogram = new HashMap<String, Long>();
		for (Object[] objects : results) {
			histogram.put((String)objects[0], (Long)objects[1]);
		}
		return histogram;
	}

	

}
