/**
 * 
 */
package com.musala.gatewaysapi.model;

/**
 * @author Rafael Bello Lara
 *
 */
public enum StatusEnum {
	online("online"),
	offline("offline");

	private final String value;

	private StatusEnum(String value) 
	{
		this.value= value;
	}
	public String getValue(){
		return value;
	}

	public static StatusEnum fromValue(String text) {
		for (StatusEnum b : StatusEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}


}
