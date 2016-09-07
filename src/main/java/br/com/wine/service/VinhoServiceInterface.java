package br.com.wine.service;

import br.com.wine.model.Vinho;

public interface VinhoServiceInterface {

	public void salvar(Vinho vinho);
	
	public Iterable<Vinho> todos();
	
	public Iterable<Vinho> porNomeContendo(String nome);
	
	public void excluir(Long codigo);
}
