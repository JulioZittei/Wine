package br.com.wine.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import br.com.wine.dto.Foto;
import br.com.wine.model.Vinho;
import br.com.wine.repository.search.PesquisaVinho;

public interface VinhoServiceInterface {

	public void salvar(Vinho vinho);
	
	public void excluir(Long codigo);
	
	public String salvarFoto(Long codigo, MultipartFile foto);
	
	public Foto excluirFoto(Long codigo);
	
	public Page<Vinho> todos(Integer page, Integer size);
	
	public Page<Vinho> pesquisaPorNome(PesquisaVinho pesquisaVinho, Integer page, Integer size);
}
