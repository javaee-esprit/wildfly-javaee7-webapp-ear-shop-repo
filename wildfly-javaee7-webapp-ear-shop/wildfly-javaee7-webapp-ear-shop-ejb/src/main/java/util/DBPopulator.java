package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import persistence.Admin;
import persistence.Category;
import persistence.Customer;
import persistence.Product;
import services.CatalogServiceLocal;
import services.TransactionServiceLocal;
import services.UserServiceLocal;

@Singleton
@Startup
public class DBPopulator {

	@EJB
	private UserServiceLocal userServiceLocal;

	@EJB
	private CatalogServiceLocal catalogServiceLocal;

	@EJB
	private TransactionServiceLocal transactionServiceLocal;

	public DBPopulator() {
	}

	@PostConstruct
	public void createData() {
		Admin admin = new Admin("admin", "admin", "company.esprit@gmail.com",10);
		Customer john = new Customer("john", "john", "john@gmail.com", "john", "doe", "20786532",toDate("17/03/2004 00:00:00"));
		Customer jenny = new Customer("jenny", "jenny", "jennyn@gmail.com", "jenny", "spencer", "98709652",toDate("23/01/1989 00:00:00"));
		Customer mohamed = new Customer("mohamed", "tounsi", "mohamed.tounsi@gmail.com", "mohamed", "tounsi", "40987621",toDate("01/06/1993 00:00:00"));
		userServiceLocal.createUser(admin);
		userServiceLocal.createUser(john);
		userServiceLocal.createUser(jenny);
		userServiceLocal.createUser(mohamed);
		
		Category booksCategory = new Category("BOOKS");
		Category smartphonescategory = new Category("SMARTPHONES");
		
		booksCategory = catalogServiceLocal.createCategory(booksCategory);
		smartphonescategory = catalogServiceLocal.createCategory(smartphonescategory);
		
		Product iphone = new Product("iPhone 6", 1600.0, 18);
		Product samsung = new Product("Samsung Note 4", 1800.0, 4);
		Product moby = new Product("Moby Dick", 12.5, 10);
		Product don = new Product("Don Quixote", 20.75, 80);
		Product  hamlet = new Product("Hamlet", 3.5, 90);
		Product  divine = new Product("The Divine Comedy ", 6.5, 1);
		Product  heart = new Product("Heart of Darkness", 30.5, 5);
		iphone.setCategory(smartphonescategory);
		samsung.setCategory(smartphonescategory);
		moby.setCategory(booksCategory);
		don.setCategory(booksCategory);
		divine.setCategory(booksCategory);
		hamlet.setCategory(booksCategory);
		heart.setCategory(booksCategory);
		iphone = catalogServiceLocal.createProduct(iphone);
		samsung = catalogServiceLocal.createProduct(samsung);
		moby = catalogServiceLocal.createProduct(moby);
		don = catalogServiceLocal.createProduct(don);
		hamlet = catalogServiceLocal.createProduct(hamlet);
		divine = catalogServiceLocal.createProduct(divine);
		heart = catalogServiceLocal.createProduct(heart);
		Integer johnId = userServiceLocal.findUserByLogin("john").getId();
		Integer jennyId = userServiceLocal.findUserByLogin("jenny").getId();
		Integer mohamedId = userServiceLocal.findUserByLogin("mohamed").getId();
			
		transactionServiceLocal
		.createTransaction(johnId, iphone.getId(), toDate("01/07/2014 19:15:48"), 2);
		transactionServiceLocal
		.createTransaction(johnId, divine.getId(), toDate("01/07/2014 19:15:48"), 1);
		transactionServiceLocal
		.createTransaction(jennyId, samsung.getId(), toDate("31/07/2014 20:59:59"), 2);
		transactionServiceLocal
		.createTransaction(mohamedId, don.getId(), toDate("12/03/2015 06:12:01"), 1);
		transactionServiceLocal
		.createTransaction(mohamedId, heart.getId(), toDate("12/03/2015 06:12:01"), 1);
		transactionServiceLocal
		.createTransaction(johnId, heart.getId(), toDate("14/03/2015 14:10:20"), 1);
		transactionServiceLocal
		.createTransaction(jennyId, heart.getId(), toDate("15/03/2015 10:00:36"), 2);
		transactionServiceLocal
		.createTransaction(mohamedId, iphone.getId(), toDate("06/11/2015 12:33:01"), 1);
		transactionServiceLocal
		.createTransaction(mohamedId, iphone.getId(), toDate("07/11/2015 8:09:43"), 1);
	}
	
    
	
	


	public  Date toDate(String formattedDate){
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.MEDIUM,Locale.FRANCE);
		Date date = null;
		try {
			date = df.parse(formattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}