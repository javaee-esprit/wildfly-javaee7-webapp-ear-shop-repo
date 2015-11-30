package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Customer;
import persistence.User;


@Remote
public interface UserServiceRemote {
	void createUser(User user);
	void saveCustomer(Customer customer);
	List<User> findAllUsers();
	User authenticate(String login, String password);
	User findUserByLogin(String login);
}
