package br.com.wine.service;

import java.util.List;

import br.com.wine.model.Vinho;

public interface VinhosServiceInterface {
	
	public void salvar(Vinho vinho);
	
	public void remover(Long codigo);
	
	public Vinho editar(Long codigo);
	
	public List<Vinho> todos();
	
	public List<Vinho> porNomeContendo(String nome);

}
