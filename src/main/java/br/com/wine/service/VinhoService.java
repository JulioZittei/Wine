package br.com.wine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.wine.dto.Foto;
import br.com.wine.model.Vinho;
import br.com.wine.repository.Vinhos;
import br.com.wine.repository.search.PesquisaVinho;
import br.com.wine.storage.FotoRemove;
import br.com.wine.storage.FotoStorage;

@Service
@Transactional(rollbackFor=Exception.class)
public class VinhoService implements VinhoServiceInterface {

	@Autowired
	private Vinhos vinhos;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Autowired(required = false)
	private FotoRemove fotoRemove;
	
	@Override
	public void salvar(Vinho vinho) {
		this.vinhos.save(vinho);
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

	@Override
	public Foto excluirFoto(Long codigo) {
		Vinho vinho  = vinhos.findOne(codigo);
		String nomeFoto = vinho.getFoto();
		vinho.setFoto(null);
		vinhos.save(vinho);	
		return new Foto(fotoRemove.getUrlDelete(nomeFoto));
	}

	@Override
	public Page<Vinho> pesquisaPorNome(PesquisaVinho pesquisaVinho, Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size);
		return vinhos.findByNomeContaining(pesquisaVinho.getNome() == null ? "" : pesquisaVinho.getNome() , pageable);
	}
	
}
