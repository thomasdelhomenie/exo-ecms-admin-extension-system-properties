package org.exoplatform.wcm.component.monitoring.systemproperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.exoplatform.commons.utils.LazyPageList;
import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.commons.utils.ListAccessImpl;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIGrid;

@ComponentConfig(template = "classpath:groovy/admin/webui/component/systemproperties/UISystemPropertiesGrid.gtmpl")
public class UISystemPropertiesList extends UIGrid {

	private static Log log = ExoLogger.getExoLogger(UISystemPropertiesList.class);

	private static String[] SYTEM_PROPERTIES_BEAN_FIELD = { "name", "value" };

	public UISystemPropertiesList() throws Exception {
		configure("name", SYTEM_PROPERTIES_BEAN_FIELD, null);
		updateGrid(20);
	}

	public List<SystemProperty> getSystemProperties() {
		List<SystemProperty> properties = new ArrayList<SystemProperty>();

		Properties systemProperties = System.getProperties();
		Iterator<Object> itSystemProperties = systemProperties.keySet().iterator();
		while (itSystemProperties.hasNext()) {
			Object key = itSystemProperties.next();
			properties.add(new SystemProperty((String) key, (String) systemProperties.get(key)));
		}

		Collections.sort(properties);

		return properties;
	}

	public void updateGrid(int itemsPerPage) throws Exception {
		ListAccess<SystemProperty> listAccess = new ListAccessImpl<SystemProperty>(SystemProperty.class, getSystemProperties());
		LazyPageList<SystemProperty> lazyPageList = new LazyPageList<SystemProperty>(listAccess, itemsPerPage);
		getUIPageIterator().setPageList(lazyPageList);
	}
}
