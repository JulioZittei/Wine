package br.com.wine.dto;

public class Foto {
	private String nome;
	
	Foto(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
