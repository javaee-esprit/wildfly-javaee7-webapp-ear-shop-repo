package services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import persistence.Category;
import persistence.Product;

@Stateless
public class CatalogService implements CatalogServiceRemote,
		CatalogServiceLocal {

	@PersistenceContext
	private EntityManager em;

	public CatalogService() {
	}

	public Product createProduct(Product product) {
		em.persist(product);
		return product;
	}

	public void saveProduct(Product product) {
		em.merge(product);
	}


	public void removeProduct(Product product) {
		em.remove(em.merge(product));
	}

	public List<Product> findAllProducts() {
		return em.createQuery("select p from Product p", Product.class)
				.getResultList();
	}

	public List<Product> findProductsByCategory(Category category) {
		return em
				.createQuery("select p from Product p where p.category=:c",
						Product.class).setParameter("c", category)
				.getResultList();
	}
	
	public Category createCategory(Category category) {
		em.persist(category);
		return category;
	}


	public void saveCategory(Category category) {
		em.merge(category);
	}

	public Category findCategoryById(int id) {
		return em.find(Category.class, id);
	}

	public void removeCategory(Category category) {
		em.remove(em.merge(category));
	}

	public List<Category> findAllCategories() {
		return em.createQuery("select c from Category c", Category.class)
				.getResultList();
	}

	public Category findCategoryByName(String name) {
		Category found = null;
		TypedQuery<Category> query = em.createQuery(
				"select c from Category c where c.name=:x", Category.class);
		query.setParameter("x", name);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"no category with name=" + name);
		}
		return found;
	}

	public byte[] findPictureByProductName(String productName) {
		byte[] picture = null;
		TypedQuery<byte[]> query = em.createQuery(
				"select p.picture from Product p where p.name=:x", byte[].class);
		query.setParameter("x", productName);
		try {
			picture = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"no picture");
		}
		return picture;

	}

	

}
