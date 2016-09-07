package br.com.wine.model;

public enum TipoVinho {

	TINTO("Tinto"),	BRANCO("Branco"),ROSE("Rosé");
	
	TipoVinho(String descricao){
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}	
