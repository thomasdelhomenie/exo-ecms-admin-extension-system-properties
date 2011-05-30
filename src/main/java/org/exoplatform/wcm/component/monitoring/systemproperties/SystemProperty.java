package org.exoplatform.wcm.component.monitoring.systemproperties;

public class SystemProperty implements Comparable<SystemProperty> {
	private String name;
	private String value;
	
	public SystemProperty(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public int compareTo(SystemProperty o) {
		return this.getName().compareTo(o.getName());
	}
}
