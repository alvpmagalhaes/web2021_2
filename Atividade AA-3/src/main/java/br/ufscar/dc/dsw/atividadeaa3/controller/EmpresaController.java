package br.ufscar.dc.dsw.atividadeaa3.controller;

import javax.validation.Valid;
import javax.xml.stream.events.EntityDeclaration;

import br.ufscar.dc.dsw.atividadeaa3.domain.Profissional;
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
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Empresa> criar(@Valid @RequestBody Empresa empresa, BCryptPasswordEncoder encoder) {
		try {
			empresa.setPassword(encoder.encode(empresa.getPassword()));
			return ResponseEntity.ok(empresaService.salvarRest(empresa));
		}catch (Exception e){
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	//listar 
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Empresa>> listar() {
		List<Empresa> lista = empresaService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
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

	//buscar
	@GetMapping(value = "/cidades/{nomeCidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Empresa> buscar(@PathVariable("nomeCidade") String nomeCidade) {
		Empresa empresa = empresaService.buscarPorCidade(nomeCidade);
		if (empresa == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empresa);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Empresa> atualizar(@Valid @RequestBody Empresa empresa, @PathVariable("id") Long id, BCryptPasswordEncoder encoder) {
		try {
			Empresa empresaOriginal = empresaService.buscarPorId(id);

			if (empresaOriginal == null) {
				return ResponseEntity.notFound().build();
			}

			String password = empresa.getPassword();

			try {
				encoder.upgradeEncoding(empresa.getPassword());
			} catch (Exception e) {
				password = encoder.encode(empresa.getPassword());
			}

			empresaOriginal.setPassword(password);
			empresaOriginal.setCnpj(empresa.getCnpj());
			empresaOriginal.setNome(empresa.getNome());
			empresaOriginal.setDescricao(empresa.getDescricao());
			empresaOriginal.setCidade(empresa.getCidade());
			empresaOriginal.setUsername(empresa.getUsername());

			return ResponseEntity.ok(empresaService.salvarRest(empresaOriginal));
		}catch (Exception e){
			return ResponseEntity.unprocessableEntity().build();
		}
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
	public String editar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (empresa.getRole() == null) {
			empresa.setRole(ROLE_EMPRESA.toString());
		}

		if (result.hasErrors()) {
			return "empresa/cadastro";
		}
		
		String password = empresa.getPassword();

		try{
			encoder.upgradeEncoding(empresa.getPassword());
		}catch (Exception e){
			password = encoder.encode(empresa.getPassword());
		}

		empresa.setPassword(password);


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
