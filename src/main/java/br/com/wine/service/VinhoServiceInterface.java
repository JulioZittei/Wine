package br.com.wine.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.wine.model.Vinho;

public interface VinhoServiceInterface {

	public void salvar(Vinho vinho);
	
	public List<Vinho> todos();
	
	public List<Vinho> porNomeContendo(String nome);
	
	public void excluir(Long codigo);
	
	public String salvarFoto(Long codigo, MultipartFile foto);
	
	public void excluirFoto(Long codigo);
}
