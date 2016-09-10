package br.com.wine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.wine.model.Vinho;
import br.com.wine.repository.Vinhos;
import br.com.wine.storage.FotoStorage;

@Service
public class VinhoService implements VinhoServiceInterface {

	@Autowired
	private Vinhos vinhos;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Override
	public void salvar(Vinho vinho) {
		this.vinhos.save(vinho);
	}

	@Override
	public List<Vinho> todos() {
		return vinhos.findAll();
	}

	@Override
	public List<Vinho> porNomeContendo(String nome) {
		return vinhos.findByNomeContaining(nome);
	}

	@Override
	public void excluir(Long codigo) {
		vinhos.delete(codigo);		
	}

	@Override
	public String salvarFoto(Long codigo, MultipartFile foto) {
		String nomeFoto = fotoStorage.salvar(foto);
		
		Vinho vinho = vinhos.findOne(codigo);
		vinho.setFoto(nomeFoto);
		vinhos.save(vinho);	
		
		return fotoStorage.getUrl(nomeFoto);
	}
	
}
