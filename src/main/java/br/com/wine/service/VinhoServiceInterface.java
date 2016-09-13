package br.com.wine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import br.com.wine.dto.Foto;
import br.com.wine.model.Vinho;

public interface VinhoServiceInterface {

	public void salvar(Vinho vinho);
	
	public List<Vinho> porNomeContendo(String nome);
	
	public void excluir(Long codigo);
	
	public String salvarFoto(Long codigo, MultipartFile foto);
	
	public Foto excluirFoto(Long codigo);
	
	public Page<Vinho> todos(Integer pagina, Integer resultados);
}
