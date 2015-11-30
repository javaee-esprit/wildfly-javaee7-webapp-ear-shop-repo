package persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer adminLevel;

	public Admin() {
	}

	public Admin(String login, String password, String email, Integer adminLevel) {
		super(login, password, email);
		this.setAdminLevel(adminLevel);
	}

	@Column(name = "level")
	public Integer getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(Integer adminLevel) {
		this.adminLevel = adminLevel;
	}

}
