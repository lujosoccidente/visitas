package com.lujos_occdente.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.servicios.VendedorService;

@Controller
@RequestMapping("/vendedores")
public class VendedoresController {
	
	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping("listar")
	public String listarVendedores(Model model, @Param("keyword") String keyword) {
		List<Vendedor> vendedores = vendedorService.obtenerVendedores(keyword);
		
		model.addAttribute("vendedores", vendedores);
		return "vendedores/listar_vendedores";
	}

	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model modelo) {
		modelo.addAttribute("vendedor", new Vendedor());
		
		return "vendedores/formulario_vendedor";
	}
	
	@PostMapping("/guardar")
	public String guardarVendedor(@ModelAttribute Vendedor vendedor, RedirectAttributes flash) {
		ErrorMensaje response = vendedorService.guardarVendedor(vendedor);
		if(Boolean.TRUE.equals(response.getError())) {
    		flash.addFlashAttribute("error", response.getMensaje());
    		return "redirect:/clientes/nuevo";
    	}
    	flash.addFlashAttribute("message", "Vendedor guardado exitosamente.");
        return "redirect:/vendedores/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarVendedor(@PathVariable Integer id, Model modelo) {
		Vendedor vendedor = vendedorService.obtenerVendedorPorId(id);
		modelo.addAttribute("vendedor", vendedor);
		return "vendedores/formulario_vendedor";
	}
	
	@DeleteMapping("/eliminar/{id}")
	public String eliminarVendedor(@PathVariable Integer id) {
		vendedorService.eliminarVendedor(id);
		return "vendedores/listar_vendedores";
	}
	
}
