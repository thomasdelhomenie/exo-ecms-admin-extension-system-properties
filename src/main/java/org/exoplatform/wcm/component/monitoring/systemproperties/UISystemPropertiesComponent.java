/***************************************************************************
 * Copyright (C) 2003-2009 eXo Platform SAS.
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
 *
 **************************************************************************/
package org.exoplatform.wcm.component.monitoring.systemproperties;

import org.exoplatform.ecm.webui.component.admin.UIECMAdminPortlet;
import org.exoplatform.ecm.webui.component.admin.UIECMAdminWorkingArea;
import org.exoplatform.ecm.webui.component.admin.listener.UIECMAdminControlPanelActionListener;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.ext.manager.UIAbstractManager;
import org.exoplatform.webui.ext.manager.UIAbstractManagerComponent;

/**
 * @author Thomas
 */

@ComponentConfig(events = { @EventConfig(listeners = UISystemPropertiesComponent.SystemPropertiesActionListener.class) })
public class UISystemPropertiesComponent extends UIAbstractManagerComponent {
	
	public static class SystemPropertiesActionListener extends UIECMAdminControlPanelActionListener<UISystemPropertiesComponent> {

		@Override
		protected void processEvent(Event<UISystemPropertiesComponent> event) throws Exception {
			UIECMAdminPortlet portlet = ((UIComponent) event.getSource()).getAncestorOfType(UIECMAdminPortlet.class);
			UIECMAdminWorkingArea uiWorkingArea = portlet.getChild(UIECMAdminWorkingArea.class);
			uiWorkingArea.setChild(UISystemPropertiesPanel.class);
			event.getRequestContext().addUIComponentToUpdateByAjax(uiWorkingArea);
		}

	}

	@Override
	public Class<? extends UIAbstractManager> getUIAbstractManagerClass() {
		return UISystemPropertiesPanel.class;
	}
}