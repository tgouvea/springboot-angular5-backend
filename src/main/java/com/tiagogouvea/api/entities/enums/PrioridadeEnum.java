package com.tiagogouvea.api.entities.enums;

public enum PrioridadeEnum {

	ALTA("Alta"),
	MEDIA("Media"),
	BAIXA("Baixa");
	
	private String value;

	private PrioridadeEnum(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return this.value;
	}
	
}
