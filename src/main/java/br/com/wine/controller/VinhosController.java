package br.com.wine.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.wine.model.TipoVinho;
import br.com.wine.model.Vinho;
import br.com.wine.service.VinhoService;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	private static final String CADASTRO_VIEW = "CadastroVinho";
	
	@Autowired
	private VinhoService vinhoService;
	
	@ModelAttribute
	private List<TipoVinho> tipoVinho(){
		return Arrays.asList(TipoVinho.values());
	}
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/vinhos/ListagemVinhos");
		mv.addObject("vinhos", vinhoService.todos());
		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		return new ModelAndView("/vinhos/CadastroVinho");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return novo(vinho);
		}
		
		try{
			vinhoService.salvar(vinho);
			attributes.addFlashAttribute("mensagem","Vinho salvo com sucesso!");
			return new ModelAndView("redirect:/vinhos/novo");
			
		}catch(RuntimeException e){
			return new ModelAndView("error/500");
		}
	}
	
	@RequestMapping(value = "/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Vinho vinho){
		ModelAndView mv = new ModelAndView("/vinhos/VizualizacaoVinho");
		mv.addObject(vinho);
		return mv;
	}
	
}
