package br.com.time11.enums;

public enum Categoria {
	ENTRETENIMENTO(1), ALIMENTACAO(2), TRANSPORTE(3);
	
	private final int id;
	
	Categoria(int id) {
		this.id = id;
	}
	
	public int getValue() {
		return id; 
	}
}
