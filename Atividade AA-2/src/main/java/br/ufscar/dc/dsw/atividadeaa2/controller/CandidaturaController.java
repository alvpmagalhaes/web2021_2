package br.ufscar.dc.dsw.atividadeaa2.controller;

import br.ufscar.dc.dsw.atividadeaa2.domain.*;
import br.ufscar.dc.dsw.atividadeaa2.security.LoginDetails;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.ILoginService;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IProfissionalService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/candidaturas")
public class CandidaturaController {
	@Autowired
	private ICandidaturaService candidaturaService;
	
	@Autowired
	private IProfissionalService profissionalService;

	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IVagaService vagaService;
	
	private Login getLoginAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginDetails user = (LoginDetails) authentication.getPrincipal();
		return loginService.buscarPorId(user.getId());
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Candidatura candidatura, ModelMap model) {
		return "candidatura/cadastro";
	}

	@GetMapping("/listar")
	public String listar(@RequestParam(required = false) String c, ModelMap model) {
		Login login = getLoginAutenticado();
		List<Candidatura> candidaturas = new ArrayList<>();

		if (TipoPermissao.ROLE_PROFISSIONAL.toString().equals(login.getRole())){
			candidaturas = candidaturaService.buscarPorProfissional(new Profissional(login));
		}

		if (TipoPermissao.ROLE_EMPRESA.toString().equals(login.getRole())){
			candidaturas = candidaturaService.buscarPorEmpresa(new Empresa(login));
		}

		if (TipoPermissao.ROLE_ADMIN.toString().equals(login.getRole())){
			candidaturas = candidaturaService.buscarTodos();
		}

		model.addAttribute("candidaturas", candidaturas);
		return "candidatura/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Candidatura candidatura, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			return "candidatura/cadastro";
		}

		Profissional profissional = new Profissional();
		profissional.setId(getLoginAutenticado().getId());
		
		candidatura.setProfissional(profissional);
		candidatura.setStatus(StatusCandidatura.ABERTO);
		candidatura.setDataCandidatura(new Date());
		candidaturaService.salvar(candidatura);
		
		attr.addFlashAttribute("sucess", "Candidatura inserida com sucesso.");
		return "redirect:/candidaturas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Candidatura candidatura = candidaturaService.buscarPorId(id);
		List<Vaga> vagas = vagaService.buscarAllPorId(candidatura.getVaga().getId());
		model.addAttribute("vagas", vagas);
		model.addAttribute("candidatura", candidatura);
		return "candidatura/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Candidatura candidatura, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("vagas", vagaService.buscarTodos());
			return "candidatura/cadastro";
		}
		
		candidatura.setProfissional(new Profissional(getLoginAutenticado()));
		candidaturaService.salvar(candidatura);
		
		attr.addFlashAttribute("sucess", "Candidatura inserida com sucesso.");
		return "redirect:/candidaturas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		candidaturaService.excluir(id);
		return "redirect:/candidaturas/listar";
	}
}
