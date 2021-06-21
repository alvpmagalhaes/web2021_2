package br.ufscar.dc.dsw.atividadeaa2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.atividadeaa2.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IUsuarioService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IProfissionalService profissionalService;

	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("emresas", profissionalervice.buscarTodos());
		return "profissional/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		profissionalService.salvar(profissional);
		attr.addFlashAttribute("success", "Profissional inserido com sucesso");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", usuarioService.buscarPorId(id));
		return "profissional/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
	
		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		usuarioService.salvar(profissional);
		attr.addFlashAttribute("success", "Profissional editadp com sucesso.");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		
			usuarioService.excluir(id);
			attr.addFlashAttribute("sucess", "Empresa excluída com sucesso.");
			return "redirect:/profissionais/listar";
	}
}
