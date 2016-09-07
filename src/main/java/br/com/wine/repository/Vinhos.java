package br.com.wine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wine.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long>{

	public List<Vinho> findByNomeContaining(String nome);
}
