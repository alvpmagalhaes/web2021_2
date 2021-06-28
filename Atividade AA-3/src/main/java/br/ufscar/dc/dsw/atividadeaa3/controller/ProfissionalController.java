package br.ufscar.dc.dsw.atividadeaa3.controller;

import javax.validation.Valid;

import br.ufscar.dc.dsw.atividadeaa3.service.spec.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.atividadeaa3.domain.Profissional;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IProfissionalService;

import java.net.URI;
import java.util.List;

import static br.ufscar.dc.dsw.atividadeaa3.domain.TipoPermissao.ROLE_PROFISSIONAL;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

	@Autowired
	private ILoginService loginService;

	@Autowired
	private IProfissionalService profissionalService;

	/**
	 * API REST Vagas de estágio/emprego
	 */


	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> salvarRest(@Valid @RequestBody Profissional profissional) {
		if (profissional.getRole() == null) {
			profissional.setRole(ROLE_PROFISSIONAL.toString());
		}
		return ResponseEntity.created(URI.create("/profissionais/"+profissionalService.salvarRest(profissional).getId())).build();
	}


	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Profissional>> listar() {
		return ResponseEntity.ok(profissionalService.buscarTodos());
	}

	/**
	 * Sistema Vagas de estágio/emprego
	 */

	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		return "profissional/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (profissional.getRole() == null) {
			profissional.setRole(ROLE_PROFISSIONAL.toString());
		}

		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		profissionalService.salvar(profissional);
		attr.addFlashAttribute("success", "Profissional inserido com sucesso");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", profissionalService.buscarPorId(id));
		return "profissional/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		if (profissional.getRole() == null) {
			profissional.setRole(ROLE_PROFISSIONAL.toString());
		}

		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		profissionalService.salvar(profissional);
		attr.addFlashAttribute("success", "Profissional editadp com sucesso.");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

			loginService.excluir(id);
			attr.addFlashAttribute("sucess", "Empresa excluída com sucesso.");
			return "redirect:/profissionais/listar";
	}
}
