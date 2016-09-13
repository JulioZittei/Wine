package br.com.wine.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.wine.model.TipoVinho;
import br.com.wine.model.Vinho;
import br.com.wine.service.VinhoService;
import br.com.wine.storage.FotoStorage;

@Controller
@RequestMapping("/vinhos")
public class VinhoController {
	
	@Autowired
	private VinhoService vinhoService;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@ModelAttribute
	public List<TipoVinho> tipoVinhos(){
		return Arrays.asList(TipoVinho.values());
	}

	@RequestMapping
	public ModelAndView todosDefault(){
		ModelAndView mv = new ModelAndView("/vinho/ListagemVinhos");
		return mv.addObject("vinhos",vinhoService.todos(0,10));	
	}
	
	@RequestMapping("/page/{page}")
	public ModelAndView todosPerPage(@PathVariable("page") Integer page){
		ModelAndView mv = new ModelAndView("/vinho/ListagemVinhos");
		return mv.addObject("vinhos",vinhoService.todos(page-1,10));	
	}
	
	@RequestMapping(value = "/novo")
	public ModelAndView novo(Vinho vinho){
		return new ModelAndView("/vinho/CadastroVinho");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(vinho);
		}
		
		try{
			vinhoService.salvar(vinho);	
			attributes.addFlashAttribute("mensagem","Vinho salvo com sucesso");
			return new ModelAndView("redirect:/vinhos/novo");
		}catch(RuntimeException e){
			return new ModelAndView("/500");
		}
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") Vinho vinho){
		ModelAndView mv = new ModelAndView("/vinho/VisualizacaoVinho");
		if(vinho.temFoto()){
			vinho.setUrl(fotoStorage.getUrl(vinho.getFoto()));
		}
		return mv.addObject(vinho);
	}
	
}
