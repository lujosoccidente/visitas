package com.lujos_occdente.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Cliente;
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.entidades.Visita;
import com.lujos_occdente.security.Usuario;
import com.lujos_occdente.servicios.ClienteService;
import com.lujos_occdente.servicios.UsuarioService;
import com.lujos_occdente.servicios.VendedorService;
import com.lujos_occdente.servicios.VisitaService;

@Controller
@RequestMapping("/visitas")
public class VisitasController {
	
	@Autowired
	private VisitaService visitaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/listar")
	public String listarVisitas(Model modelo, @Param("keyword") String keyword,
			@Param("fechaInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicial,
			@Param("fechaFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		List<Visita> visitas = new ArrayList<>();
		if(usuario.getRol().getRole_name().equals("ROLE_ADMIN")) {
			visitas = visitaService.buscarVisitas(keyword, fechaInicial, fechaFinal);			
		}else if(usuario.getRol().getRole_name().equals("ROLE_VENDEDOR")){
			visitas = visitaService.buscarVisitasPorVendedor(keyword, fechaInicial, fechaFinal, usuario.getVendedor());
		}
		modelo.addAttribute("visitas", visitas);
		return "visitas/listar_visitas";
	}

	@GetMapping("/nueva")
	public String formlarioVisita(Model modelo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		List<Cliente> clientes = new ArrayList<>();
		if(usuario.getRol().getRole_name().equalsIgnoreCase("ROLE_ADMIN")) {
			clientes = clienteService.obtenerTodosClientesActivos();			
		}else {
			clientes = clienteService.obtenerClientesVendedor(usuario.getVendedor());
		}
		System.out.println(clientes.size());
		List<Vendedor> vendedores = vendedorService.obtenerVendedoresActivos();
		modelo.addAttribute("clientes", clientes);
		modelo.addAttribute("vendedores", vendedores);
		modelo.addAttribute("visita", new Visita());
		return "visitas/formulario_visita";
	}
	
	@GetMapping("/editar/{idVisita}")
	public String editarVisita(Model modelo, @PathVariable Integer idVisita) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
		Visita visita = visitaService.obtenerVisitaPorId(idVisita);
		List<Cliente> clientes = new ArrayList<>();
		if(usuario.getRol().getRole_name().equalsIgnoreCase("ROLE_ADMIN")) {
			clientes = clienteService.obtenerTodosClientesActivos();			
		}else {
			clientes = clienteService.obtenerClientesVendedor(usuario.getVendedor());
		}
		System.out.println(clientes.size());
		List<Vendedor> vendedores = vendedorService.obtenerVendedoresActivos();

		modelo.addAttribute("clientes", clientes);
		modelo.addAttribute("vendedores", vendedores);
		modelo.addAttribute("visita", visita);
		return "visitas/formulario_visita";
	}
	
	@PostMapping("/guardar")
	public String guardarVisita(@ModelAttribute Visita visita, RedirectAttributes flash) {
		ErrorMensaje response = visitaService.guardarVisita(visita);
		if(Boolean.TRUE.equals(response.getError())) {
			flash.addFlashAttribute("error", response.getMensaje());
			return "redirect:/visitas/nueva";
		}
		flash.addFlashAttribute("message", response.getMensaje());
		return "redirect:/visitas/listar";
	}
	
	@DeleteMapping("/eliminar/{id}")
	public String eliminarVisita(@PathVariable Integer id) {
		visitaService.eliminarVisita(id);
		return "visitas/listar_visitas";
	}

}
