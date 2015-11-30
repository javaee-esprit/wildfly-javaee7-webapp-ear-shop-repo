package presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import persistence.Customer;
import services.UserServiceLocal;

@ManagedBean
@RequestScoped
public class RegisterBean {

	@EJB
	private UserServiceLocal userServiceLocal;

	@ManagedProperty("#{authBean}")
	private AuthenticationBean authBean;

	private Customer customer;

	public RegisterBean() {
	}

	@PostConstruct
	public void init() {
		customer = new Customer();
	}

	public String doSignUp() {
		String navigateTo = null;
		userServiceLocal.saveCustomer(customer);
		authBean.setUser(customer);
		navigateTo = authBean.doLogin();
		return navigateTo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void validateLoginUnicity(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		String loginToValidate = (String) value;
		if (loginToValidate == null || loginToValidate.trim().isEmpty()) {
			return;
		}
		boolean loginInUse = userServiceLocal.loginExists(loginToValidate);
		if (loginInUse) {
			throw new ValidatorException(new FacesMessage(
					"login already in use!"));
		}
	}

	public AuthenticationBean getAuthBean() {
		return authBean;
	}

	public void setAuthBean(AuthenticationBean authBean) {
		this.authBean = authBean;
	}

}
