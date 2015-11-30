package presentation.mbeans;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

import persistence.Category;
import services.CatalogServiceLocal;

@ManagedBean
@ApplicationScoped
public class HelperBean {

	@EJB
	private CatalogServiceLocal catalog;

	private byte[] defaultPicture;

	public HelperBean() {
	}

	@PostConstruct
	public void init() {

		InputStream is = FacesContext.getCurrentInstance().getExternalContext()
				.getResourceAsStream("/resources/img/default.jpg");

		try {
			defaultPicture = IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Category findCategoryByName(String name) {
		return catalog.findCategoryByName(name);
	}

	public byte[] getDefaultPicture() {
		return defaultPicture;
	}

	public void setDefaultPicture(byte[] defaultPicture) {
		this.defaultPicture = defaultPicture;
	}

}
