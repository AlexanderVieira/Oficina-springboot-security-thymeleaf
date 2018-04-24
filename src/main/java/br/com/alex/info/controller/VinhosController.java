package br.com.alex.info.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alex.info.model.TipoVinho;
import br.com.alex.info.model.Vinho;
import br.com.alex.info.repository.filter.VinhoFilter;
import br.com.alex.info.services.impl.VinhoServiceImpl;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	@Autowired
	private VinhoServiceImpl vinhoServiceImpl;
	
	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView mv = new ModelAndView("vinho/cadastro-vinho");
		mv.addObject(vinho);
		mv.addObject("tipos", TipoVinho.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(vinho);
		}
		
		vinhoServiceImpl.salvar(vinho);
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso!");
		return new ModelAndView("redirect:/vinhos/novo");
	}
	
	@GetMapping
	public ModelAndView buscar(VinhoFilter vinhoFilter) {
		ModelAndView mv = new ModelAndView("/vinho/pesquisa-vinhos");
		mv.addObject("vinhos", vinhoServiceImpl.buscarPorNome(vinhoFilter));
		return mv;		
		
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Vinho vinho = vinhoServiceImpl.buscarPorId(codigo);
		return novo(vinho);
		
	}
	
	@DeleteMapping("/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		vinhoServiceImpl.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Vinho removido com sucesso!");
		return "redirect:/vinhos";
	}

}
