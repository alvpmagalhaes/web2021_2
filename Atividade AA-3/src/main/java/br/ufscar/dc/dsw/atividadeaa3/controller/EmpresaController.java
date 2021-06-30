package br.ufscar.dc.dsw.atividadeaa3.controller;

import javax.validation.Valid;

import br.ufscar.dc.dsw.atividadeaa3.service.spec.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.atividadeaa3.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa3.service.spec.IEmpresaService;

import static br.ufscar.dc.dsw.atividadeaa3.domain.TipoPermissao.ROLE_EMPRESA;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private ILoginService loginService;

	@Autowired
	private IEmpresaService empresaService;
	
	/**
	 * API REST Vagas de estágio/emprego
	 */
	
	
	//salvar 
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> criar(@Valid @RequestBody Empresa empresa, BCryptPasswordEncoder encoder) {
		empresa.setPassword(encoder.encode(empresa.getPassword()));
		return ResponseEntity.created(URI.create("/empresas/"+empresaService.salvarRest(empresa).getId())).build();
	}
	
	//listar 
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Empresa>> listar() {
		return ResponseEntity.ok(empresaService.buscarTodos());
	}
	
	//buscar
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Empresa> buscar(@PathVariable("id") Long id) {
		Empresa empresa = empresaService.buscarPorId(id);
		if (empresa == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empresa);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Empresa> atualizar(@Valid @RequestBody Empresa empresa, @PathVariable("id") Long id, BCryptPasswordEncoder encoder) {
		Empresa empresaOriginal = empresaService.buscarPorId(id);

		if(empresaOriginal == null){
			return ResponseEntity.notFound().build();
		}

		empresaOriginal.setPassword(encoder.encode(empresa.getPassword()));
		empresaOriginal.setCnpj(empresa.getCnpj());
		empresaOriginal.setNome(empresa.getNome());
		empresaOriginal.setDescricao(empresa.getDescricao());
		empresaOriginal.setCidade(empresa.getCidade());
		empresa.setUsername(empresa.getUsername());

		return ResponseEntity.ok(empresaService.salvarRest(empresa));
	}

	//remover
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable("id") Long id) {
		Empresa empresa = empresaService.buscarPorId(id);
		if (empresa == null){
			return ResponseEntity.notFound().build();
		}
		loginService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	
	/**
	 * Sistema Vagas de estágio/emprego
	 */
	

	@GetMapping("/cadastrar")
	public String cadastrar(Empresa empresa) {
		return "empresa/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("empresas", empresaService.buscarTodos());
		return "empresa/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (empresa.getRole() == null) {
			empresa.setRole(ROLE_EMPRESA.toString());
		}

		if (result.hasErrors()) {
			return "empresa/cadastro";
		}
		empresa.setPassword(encoder.encode(empresa.getPassword()));
		empresaService.salvar(empresa);
		attr.addFlashAttribute("sucess", "Empresa inserida com sucesso");
		return "redirect:/empresas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("empresa", empresaService.buscarPorId(id));
		return "empresa/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {
		if (empresa.getRole() == null) {
			empresa.setRole(ROLE_EMPRESA.toString());
		}

		if (result.hasErrors()) {
			return "empresa/cadastro";
		}

		empresaService.salvar(empresa);
		attr.addFlashAttribute("sucess", "Empresa editada com sucesso.");
		return "redirect:/empresas/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		
			loginService.excluir(id);
			attr.addFlashAttribute("sucess", "Empresa excluída com sucesso.");
			return "redirect:/empresas/listar";
	}
}
