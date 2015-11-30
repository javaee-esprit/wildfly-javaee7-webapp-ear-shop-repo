package services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import persistence.Customer;
import persistence.User;

@Stateless
public class UserService implements UserServiceRemote, UserServiceLocal {

	@PersistenceContext
	private EntityManager em;

	public UserService() {
	}

	public void createUser(User user) {
		em.persist(user);
	}

	public void saveCustomer(Customer customer) {
		em.merge(customer);
	}

	public User authenticate(String login, String password) {
		User found = null;
		String jpql = "select u from User u where u.login=:login and u.password=:password";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(UserService.class.getName()).log(
					Level.WARNING,
					"authentication attempt failure with login=" + login
							+ " and password=" + password);
		}
		return found;
	}

	public List<User> findAllUsers() {
		return em.createQuery("select u from User u", User.class)
				.getResultList();
	}

	public boolean loginExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from User u where u.login=:login";
		TypedQuery<Boolean> query = em.createQuery(jpql, Boolean.class);
		query.setParameter("login", login);
		try {
			exists = query.getSingleResult();
		} catch (NoResultException e) {
			Logger.getLogger(UserService.class.getName()).log(Level.WARNING,
					"no user registred with login=" + login);
		}
		return exists;
	}

	public User findUserByLogin(String login) {
		User found = null;
		String jpql = "select u from User u where u.login=:login";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("login", login);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(UserService.class.getName()).log(Level.WARNING,
					"no such login=" + login);
		}
		return found;
	}

}
