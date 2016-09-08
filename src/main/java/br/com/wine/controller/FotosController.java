package br.com.wine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.wine.dto.Foto;
import br.com.wine.service.VinhoService;

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private VinhoService vinhoService;
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public Foto upload(@PathVariable("codigo") Long codigo, @RequestParam("files[]") MultipartFile[] files) {
		String url = vinhoService.salvarFoto(codigo, files[0]);
		return new Foto(url);
	}
	
}
