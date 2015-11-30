package presentation.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import persistence.Category;
import presentation.mbeans.HelperBean;


@FacesConverter("cc")
public class CategoryConverter implements Converter{
	
	

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Category equivalentCategory = null;
		if(!value.trim().equals("")){
			HelperBean helper = context.getApplication().evaluateExpressionGet(context, "#{helperBean}", HelperBean.class);
			equivalentCategory = helper.findCategoryByName(value);
		}
		return equivalentCategory;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String equivalentString = null;
		if(value == null || value.equals("")){
			equivalentString = "";
		}else{
			equivalentString = ((Category)value).getName();
		}
		return equivalentString;
	}

}
