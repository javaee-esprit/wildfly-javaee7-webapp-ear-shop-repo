package services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import persistence.Customer;
import persistence.Product;

@Local
public interface TransactionServiceLocal {

	Boolean createTransaction(Integer customerId, Integer productId, Date date,
			Integer quantity);

	List<Product> findProductsByCustomer(Integer customerId);

	/**
	 * 
	 * @param customer
	 * @return a list of purchases</br> each purchase is represented by a map
	 *         that has the following keys</br> -productName</br>
	 *         -productUnitCost</br> -date</br> -quantity</br>
	 */
	List<Map<String, Object>> purchasesByCustomer(Customer customer);
	/**
	 * 
	 * @return a map representation of products sales</br>
	 * key : product's name</br>
	 * value : number of items sold</br> 
	 */
	Map<String, Long> sales();

}
