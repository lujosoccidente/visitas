package com.lujos_occdente.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Cliente;
import com.lujos_occdente.entidades.Contacto;
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.repositorios.ClienteRepository;
import com.lujos_occdente.servicios.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Override
	public List<Cliente> obtenerClientes(String nombre) {
		if(nombre !=null && !nombre.isEmpty()) {
			return clienteRepo.findByRazonSocialContainingAndEstadoTrue(nombre);
		}
		return clienteRepo.findByEstadoTrue();
	}

	@Override
	public ErrorMensaje guardarCliente(Cliente cliente) {
		Cliente clienteNuevo = null;
		if(cliente.getCliente_id() != null) {
			clienteNuevo = clienteRepo.findById(cliente.getCliente_id())
					.orElseThrow(()->new NotFoundException("No se encontro cliente."));			
		}else {
			clienteNuevo = new Cliente();			
		}
		clienteNuevo.setRazonSocial(cliente.getNombres() + " " + cliente.getApellidos());
		clienteNuevo.setNombres(cliente.getNombres());
		clienteNuevo.setApellidos(cliente.getApellidos());
		clienteNuevo.setDireccion(cliente.getDireccion());
		clienteNuevo.setTelefono(cliente.getTelefono());
		clienteNuevo.setEmail(cliente.getEmail());
		clienteNuevo.setTipo_cliente(cliente.getTipo_cliente());
		clienteNuevo.setNit(cliente.getNit());
		clienteNuevo.setCond_pago(cliente.getCond_pago());
		
		List<Contacto> contactos = cliente.getContactos();
		if(!contactos.isEmpty()) {
			for(Contacto contacto : contactos) {
				clienteNuevo.agregarContacto(contacto);
			}
		}
		try {
			clienteRepo.save(clienteNuevo);		
			return new ErrorMensaje(false, "");
		} catch (DataIntegrityViolationException e) {
			return new ErrorMensaje(true, "El cliente con el nit " + cliente.getNit() + " ya existe.");
		}
		
	}

	@Transactional
	@Override
	public void eliminarCliente(Integer id) {
		clienteRepo.deactivate(id);
	}

	@Override
	public Cliente obtenerClientePorId(Integer id) {
		return clienteRepo.findById(id)
				.orElseThrow(()->new NotFoundException("No se encontro cliente."));
	}

	@Override
	public List<Cliente> obtenerClientesVendedor(Vendedor vendedor) {
		return clienteRepo.findByVendedor(vendedor);
	}

	@Override
	public List<Cliente> obtenerTodosClientesActivos() {
		return clienteRepo.findByEstadoTrue();
	}



}
