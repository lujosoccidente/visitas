package com.lujos_occdente.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.security.Rol;
import com.lujos_occdente.security.RolService;
import com.lujos_occdente.security.Usuario;
import com.lujos_occdente.servicios.UsuarioService;
import com.lujos_occdente.servicios.VendedorService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping("/listar")
	public String listarUsuarios(Model modelo) {
		List<Usuario> usuarios = usuarioService.obtenerUsuariosActivos();
		modelo.addAttribute("usuarios", usuarios);
		return "usuarios/listar_usuarios";
	}
	
	@GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        List<Rol> roles = rolService.obtenerRolesActivos();
        List<Vendedor> vendedores = vendedorService.obtenerVendedoresActivos();
        System.out.println(vendedores);
        model.addAttribute("roles", roles);
        model.addAttribute("vendedores", vendedores);
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("editar", false);
        return "usuarios/formulario_usuario";
    }
	
	@PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes flash) {
    	ErrorMensaje response = usuarioService.guardarUsuario(usuario);
    	if(Boolean.TRUE.equals(response.getError())) {
    		flash.addFlashAttribute("error", response.getMensaje());
    		return "redirect:/usuarios/nuevo";
    	}
    	flash.addFlashAttribute("message", "Usuario guardado exitosamente.");
        return "redirect:/usuarios/listar";
    }
	
	@GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
    	Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
    	List<Vendedor> vendedores = vendedorService.obtenerVendedoresActivos();
    	List<Rol> roles = rolService.obtenerRolesActivos();
    	model.addAttribute("vendedores", vendedores);
        model.addAttribute("roles", roles);
		model.addAttribute("usuario", usuario);
		model.addAttribute("editar", true);
    	return "usuarios/formulario_usuario";
    }

}
