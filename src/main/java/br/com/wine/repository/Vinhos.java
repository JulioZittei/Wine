package br.com.wine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.wine.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long> {

	public Page<Vinho> findByNomeContaining(String nome, Pageable pageable);
	
	public Page<Vinho> findAllByOrderByNomeDesc(Pageable pageable);
}
