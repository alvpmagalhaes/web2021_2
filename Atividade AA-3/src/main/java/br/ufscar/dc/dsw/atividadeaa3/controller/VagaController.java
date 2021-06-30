package br.ufscar.dc.dsw.atividadeaa3.controller;

import br.ufscar.dc.dsw.atividadeaa3.domain.*;
import br.ufscar.dc.dsw.atividadeaa3.security.LoginDetails;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.ILoginService;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.net.URI;


@RestController
@RequestMapping("/vagas")
public class VagaController {
	
	@Autowired
	private IEmpresaService empresaService;

	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IVagaService vagaService;
	
	private Login getEmpresaAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			LoginDetails user = (LoginDetails) authentication.getPrincipal();
			return loginService.buscarPorId(user.getId());
		}catch (Exception e){
			return null;
		}
	}
	
	
	/**
	 * API REST Vagas de estágio/emprego
	 */


	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> salvarRest(@Valid @RequestBody Vaga vaga) {
		if (vaga.getRole() == null) {
			vaga.setRole(ROLE_VAGA.toString());
		}
		return ResponseEntity.created(URI.create("/vaga/"+vagaService.salvarRest(vaga).getId())).build();
	}


	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vaga>> listar() {
		return ResponseEntity.ok(vagaService.buscarTodos());
	}
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vaga> buscar(@PathVariable("id") Long id) {
		Vaga vaga = vagaService.buscarPorId(id);
		if (vaga == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(vaga);
	}


	/**
	 * Sistema Vagas de estágio/emprego
	 */

	
	@GetMapping("/cadastrar")
	public String cadastrar(Vaga vaga, ModelMap model) {
		return "vaga/cadastro";
	}

	@GetMapping("/listar")
	public String listar(@RequestParam(required = false) String c, ModelMap model) {
		Login login = getEmpresaAutenticado();

		List<Vaga> vagas = vagaService.buscarTodos();
		Set<String> cidades = new HashSet<String>();

		for (Vaga vaga : vagas) {
			String cidade = vaga.getEmpresa().getCidade();
			if (!cidades.contains(cidade)) {
				cidades.add(cidade);
			}
		}

		if (login != null && TipoPermissao.ROLE_EMPRESA.toString().equals(login.getRole())) {
			Empresa empresa = new Empresa();
			empresa.setId(login.getId());
			vagas = vagaService.buscarPorEmpresa(empresa);
		}else {
			if (c != null && !c.isEmpty()) {
				vagas = vagaService.buscarTodos(c);
			}
			model.addAttribute("cidades", cidades);
		}
		model.addAttribute("vagas", vagas);
		return "vaga/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			return "vaga/cadastro";
		}

		Login login = getEmpresaAutenticado();
		Empresa empresa = new Empresa();
		empresa.setId(login.getId());
		
		vaga.setEmpresa(empresa);
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

		Login login = getEmpresaAutenticado();
		Empresa empresa = new Empresa();
		empresa.setId(login.getId());
		
		vaga.setEmpresa(empresa);
		vagaService.salvar(vaga);
		
		attr.addFlashAttribute("sucess", "Vaga inserida com sucesso.");
		return "redirect:/vagas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		vagaService.excluir(id);
		return "redirect:/vagas/listar";
	}

	@GetMapping("/candidatar/{id}")
	public String candidatar(@PathVariable("id") Long id, ModelMap model) {
		List<Vaga> vagas = vagaService.buscarAllPorId(id);
		model.addAttribute("vagas", vagas);
		model.addAttribute("candidatura", new Candidatura());
		return "candidatura/cadastro";
	}
}
