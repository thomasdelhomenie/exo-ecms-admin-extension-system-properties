/*
 * Copyright (C) 2003-2007 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.wcm.component.monitoring.systemproperties;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.lifecycle.UIContainerLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.ext.manager.UIAbstractManager;

@ComponentConfig(lifecycle = UIContainerLifecycle.class)
public class UISystemPropertiesPanel extends UIAbstractManager {

	public UISystemPropertiesPanel() throws Exception {
		UISystemPropertiesFilterForm uiSystemPropertiesFilterForm = addChild(UISystemPropertiesFilterForm.class, null, null);
		uiSystemPropertiesFilterForm.setOptions(getItemsPerPageOptionsOptions());
		addChild(UISystemPropertiesList.class, null, "SystemPropertiesList");
	}

	private List<SelectItemOption<String>> getItemsPerPageOptionsOptions() throws Exception {
		List<SelectItemOption<String>> options = new ArrayList<SelectItemOption<String>>();
		options.add(new SelectItemOption<String>("10", "10"));
		options.add(new SelectItemOption<String>("20", "20"));
		options.add(new SelectItemOption<String>("50", "50"));
		options.add(new SelectItemOption<String>("100", "100"));
		return options;
	}

	public void refresh() throws Exception {
		UISystemPropertiesFilterForm systemPropertiesFilterForm = getChild(UISystemPropertiesFilterForm.class);
		String itemsPerPage = systemPropertiesFilterForm.getUIFormSelectBox(UISystemPropertiesFilterForm.FIELD_SELECT_ITEMS_PER_PAGE).getValue();
		if (itemsPerPage == null) {
			systemPropertiesFilterForm.setOptions(getItemsPerPageOptionsOptions());
			itemsPerPage = systemPropertiesFilterForm.getUIFormSelectBox(UISystemPropertiesFilterForm.FIELD_SELECT_ITEMS_PER_PAGE).getValue();
		}
		UISystemPropertiesList uiSystemPropertiesList = getChildById("SystemPropertiesList");
		uiSystemPropertiesList.updateGrid(Integer.valueOf(itemsPerPage));
	}
}
