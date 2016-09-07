package br.com.wine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.wine.model.Vinho;
import br.com.wine.repository.Vinhos;

@Service
public class VinhoService implements VinhoServiceInterface {

	@Autowired
	private Vinhos vinhos;
	
	@Override
	public void salvar(Vinho vinho) {
		this.vinhos.save(vinho);
	}

	@Override
	public Iterable<Vinho> todos() {
		return vinhos.findAll();
	}

	@Override
	public Iterable<Vinho> porNomeContendo(String nome) {
		return vinhos.findByNomeContaining(nome);
	}

	@Override
	public void excluir(Long codigo) {
		vinhos.delete(codigo);		
	}
}
