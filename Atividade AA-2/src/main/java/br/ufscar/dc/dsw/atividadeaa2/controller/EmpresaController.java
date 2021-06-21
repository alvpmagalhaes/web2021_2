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

import br.ufscar.dc.dsw.atividadeaa2.domain.Empresa;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.atividadeaa2.service.spec.IUsuarioService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IEmpresaService empresaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Empresa empresa) {
		return "cliente/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("emresas", empresaService.buscarTodos());
		return "empresa/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (empresa.getRole() == null) {
			empresa.setRole("ROLE_CLIENTE");
		}

		if (result.hasErrors()) {
			return "empresa/cadastro";
		}
		empresa.setPassword(encoder.encode(empresa.getPassword()));
		usuarioService.salvar(empresa);
		attr.addFlashAttribute("sucess", "Empresa inserida com sucesso");
		return "redirect:/empresas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("empresa", usuarioService.buscarPorId(id));
		return "empresa/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {
	
		if (result.hasErrors()) {
			return "empresa/cadastro";
		}

		usuarioService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Empresa editada com sucesso.");
		return "redirect:/empresas/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		
			usuarioService.excluir(id);
			attr.addFlashAttribute("sucess", "Empresa excluída com sucesso.");
			return "redirect:/clientes/listar";
	}
}
