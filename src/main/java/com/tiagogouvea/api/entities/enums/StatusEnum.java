package com.tiagogouvea.api.entities.enums;

public enum StatusEnum {

	NOVO("Novo"),
	ANDAMENTO("Andamento"),
	RESOLVIDO("REsolvido"),
	APPROVADO("Aprovado"),
	REPROVADO("Reprovado"),
	FECHADO("Fechado");
	
	private String value;

	private StatusEnum(String value) {
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
