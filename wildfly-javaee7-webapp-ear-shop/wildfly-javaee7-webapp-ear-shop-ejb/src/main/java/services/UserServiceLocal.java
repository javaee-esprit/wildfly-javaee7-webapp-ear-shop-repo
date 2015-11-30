package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Customer;
import persistence.User;
@Local
public interface UserServiceLocal {
	void createUser(User user);
	void saveCustomer(Customer customer);
	List<User> findAllUsers();
	User authenticate(String login, String password);
	boolean loginExists(String login);
	User findUserByLogin(String login);
}
