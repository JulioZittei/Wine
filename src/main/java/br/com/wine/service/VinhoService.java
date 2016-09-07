package br.com.wine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wine.model.Vinho;
import br.com.wine.repository.Vinhos;

@Service
public class VinhoService implements VinhosServiceInterface{
	
	@Autowired
	private Vinhos vinhos;
	
	@Override
	public void salvar(Vinho vinho) {
		vinhos.save(vinho);
	}

	@Override
	public void remover(Long codigo) {
		vinhos.delete(codigo);
	}

	@Override
	public Vinho editar(Long codigo) {
		return vinhos.findOne(codigo);
	}

	@Override
	public List<Vinho> todos() {
		return vinhos.findAll();
	}

	@Override
	public List<Vinho> porNomeContendo(String nome) {
		return vinhos.findByNomeContaining(nome);
	}

}
