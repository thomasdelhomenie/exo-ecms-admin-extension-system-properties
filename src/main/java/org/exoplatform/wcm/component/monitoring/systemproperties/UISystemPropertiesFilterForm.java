package org.exoplatform.wcm.component.monitoring.systemproperties;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormSelectBox;

/**
 * Form for the "Nb of items per page" selectbox
 * @author thomas
 *
 */
@ComponentConfig(lifecycle = UIFormLifecycle.class, template = "classpath:groovy/admin/webui/component/systemproperties/UISystemPropertiesFilterForm.gtmpl", events = { @EventConfig(listeners = UISystemPropertiesFilterForm.ChangeActionListener.class) })
public class UISystemPropertiesFilterForm extends UIForm {
	final static public String FIELD_SELECT_ITEMS_PER_PAGE = "rowsPerPage";

	public UISystemPropertiesFilterForm() throws Exception {
		UIFormSelectBox scriptItemsPerPage = new UIFormSelectBox(FIELD_SELECT_ITEMS_PER_PAGE, FIELD_SELECT_ITEMS_PER_PAGE,
				new ArrayList<SelectItemOption<String>>());
		scriptItemsPerPage.setOnChange("Change");
		addUIFormInput(scriptItemsPerPage);
	}

	public void setOptions(List<SelectItemOption<String>> options) {
		getUIFormSelectBox(FIELD_SELECT_ITEMS_PER_PAGE).setOptions(options);
		getUIFormSelectBox(FIELD_SELECT_ITEMS_PER_PAGE).setSelectedValues(new String[] {"20"});
	}

	static public class ChangeActionListener extends EventListener<UISystemPropertiesFilterForm> {
		public void execute(Event<UISystemPropertiesFilterForm> event) throws Exception {
			UISystemPropertiesFilterForm uiForm = event.getSource();
			UISystemPropertiesPanel uiSystemPropertiesPanel = uiForm.getParent();
			UISystemPropertiesList uiSystemPropertiesList = uiSystemPropertiesPanel.getChildById("SystemPropertiesList");
			String itemsPerPage = uiForm.getUIFormSelectBox(FIELD_SELECT_ITEMS_PER_PAGE).getValue();
			uiSystemPropertiesList.updateGrid(Integer.valueOf(itemsPerPage));
			
			Class<?>[] renderedChildren = {UISystemPropertiesFilterForm.class, UISystemPropertiesList.class};
			
			uiSystemPropertiesPanel.setRenderedChildrenOfTypes(renderedChildren);
			event.getRequestContext().addUIComponentToUpdateByAjax(uiSystemPropertiesPanel);
		}
	}

}
