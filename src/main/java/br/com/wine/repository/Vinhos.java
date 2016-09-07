package br.com.wine.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.wine.model.Vinho;

public interface Vinhos extends CrudRepository<Vinho, Long> {

	public Iterable<Vinho> findByNomeContaining(String nome);
}
