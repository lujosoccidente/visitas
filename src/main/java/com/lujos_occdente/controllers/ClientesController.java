package com.lujos_occdente.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Cliente;
import com.lujos_occdente.entidades.CondicionPago;
import com.lujos_occdente.entidades.Contacto;
import com.lujos_occdente.entidades.TipoCliente;
import com.lujos_occdente.servicios.ClienteService;
import com.lujos_occdente.servicios.CondicionesPagoService;
import com.lujos_occdente.servicios.TiposClienteService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TiposClienteService tipoClienteService;
	
	@Autowired
	private CondicionesPagoService condicionesPagosServices;
	
	@GetMapping("/listar")
	public String listarClientes(Model model, @Param("keyword") String keyword) {
		List<Cliente> clientes = clienteService.obtenerClientes(keyword);
		
		model.addAttribute("clientes", clientes);
		return "clientes/listar_clientes";
	}
	
	@GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        List<TipoCliente> tiposClientes = tipoClienteService.obtenerTodosTiposCliente();
		List<CondicionPago> condicionesPago = condicionesPagosServices.obtenerCondicionesPago();
        List<Contacto> contactos = new ArrayList<>();
		model.addAttribute("tiposClientes", tiposClientes);
        model.addAttribute("condicionesPago", condicionesPago);
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("contactos", contactos);
		model.addAttribute("editar", false);
        return "clientes/formulario_cliente";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente, RedirectAttributes flash,
    		@RequestParam Map<String, String> requestParams) {
    	System.out.println(cliente);
        
    	ErrorMensaje response = clienteService.guardarCliente(cliente);
    	if(Boolean.TRUE.equals(response.getError())) {
    		flash.addFlashAttribute("error", response.getMensaje());
    		return "redirect:/clientes/nuevo";
    	}
    	flash.addFlashAttribute("message", "Cliente guardado exitosamente.");
        return "redirect:/clientes/listar";
    }
    
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Integer id, Model model) {
    	Cliente cliente = clienteService.obtenerClientePorId(id);
    	List<TipoCliente> tiposClientes = tipoClienteService.obtenerTodosTiposCliente();
		List<CondicionPago> condicionesPago = condicionesPagosServices.obtenerCondicionesPago();
        model.addAttribute("tiposClientes", tiposClientes);
        model.addAttribute("condicionesPago", condicionesPago);
		model.addAttribute("cliente", cliente);
		model.addAttribute("editar", true);
    	return "clientes/formulario_cliente";
    }
       
    @DeleteMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable String id, RedirectAttributes flash) {
    	try {
            Integer clienteId = Integer.parseInt(id);
            clienteService.eliminarCliente(clienteId);
            flash.addFlashAttribute("message", "Cliente eliminado exitosamente.");
        } catch (NumberFormatException e) {
            flash.addFlashAttribute("error", "Error al eliminar el cliente. ID no válido.");
        }
    	return "clientes/listar_clientes";
    }

}
