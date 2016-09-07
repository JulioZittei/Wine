package br.com.wine.controller;

import org.springframework.data.solr.core.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fotos")
public class FotosController {

	@RequestMapping(method = RequestMethod.POST)
	public Foto upload(@RequestParam("files[]") MultipartFile[] files){
		
	}
}
