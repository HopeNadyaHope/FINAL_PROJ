package by.epam.lobanok.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceProperty {
	private ResourceBundle bundle;
	
	public ResourceProperty(Locale locale) {
		bundle = ResourceBundle.getBundle("local", locale);
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}
