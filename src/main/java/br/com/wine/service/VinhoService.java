package br.com.wine.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.wine.model.Vinho;
import br.com.wine.repository.Vinhos;

@Service
public class VinhoService implements VinhoServiceInterface {

	@Autowired
	private Vinhos vinhos;
	
	@Autowired
	private AmazonS3 s3Client;
	
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
		Vinho vinho = vinhos.findOne(codigo);
		String nomeFoto = foto.getOriginalFilename();
		vinho.setFoto(nomeFoto);
		vinhos.save(vinho);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(foto.getContentType());
		metadata.setContentLength(foto.getSize());
		try {
			s3Client.putObject("wine",nomeFoto,foto.getInputStream(),metadata);
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException("Erro salvando arquivo no S3", e);
		}
		return "http://localhost:9444/ui/wine/"+nomeFoto+"?noAuth=true";
	}
	
}
