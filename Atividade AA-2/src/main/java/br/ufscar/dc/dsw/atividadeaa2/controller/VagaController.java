package br.ufscar.dc.dsw.atividadeaa2.controller;

import br.ufscar.dc.dsw.atividadeaa2.domain.Candidatura;
import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.domain.StatusCandidatura;
import br.ufscar.dc.dsw.atividadeaa2.domain.Vaga;
import br.ufscar.dc.dsw.atividadeaa2.security.LoginDetails;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/vagas")
public class VagaController {
	
	@Autowired
	private IEmpresaService empresaService;
	
	@Autowired
	private IVagaService vagaService;
	
	private Empresa getEmpresaAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginDetails user = (LoginDetails) authentication.getPrincipal();
		return empresaService.buscarPorId(user.getId());
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Candidatura candidatura, ModelMap model) {
		return "vaga/cadastro";
	}

	@GetMapping("/listar")
	public String listar(@RequestParam(required = false) String c, ModelMap model) {
		List<Vaga> vagas = vagaService.buscarPorEmpresa(getEmpresaAutenticado());

		model.addAttribute("vagas", vagas);
		return "vaga/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			return "vaga/cadastro";
		}
		
		vaga.setEmpresa(getEmpresaAutenticado());
		vagaService.salvar(vaga);
		
		attr.addFlashAttribute("sucess", "Vaga inserida com sucesso.");
		return "redirect:/vagas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("vaga", vagaService.buscarPorId(id));
		return "vaga/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			return "vaga/cadastro";
		}
		
		vaga.setEmpresa(getEmpresaAutenticado());
		vagaService.salvar(vaga);
		
		attr.addFlashAttribute("sucess", "Vaga inserida com sucesso.");
		return "redirect:/vagas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		vagaService.excluir(id);
		return "redirect:/vagas/listar";
	}
}
