package com.lujos_occdente.servicios.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.repositorios.VendedorRepository;
import com.lujos_occdente.servicios.VendedorService;

@Service
public class VendedorServiceImpl implements VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepo;

	@Override
	public List<Vendedor> obtenerVendedores(String nombres) {
		if(nombres != null && !nombres.isEmpty()) {
			return vendedorRepo.findByNombresContainingAndEstadoTrue(nombres);
		}
		return vendedorRepo.findByEstadoTrue();
	}

	@Override
	public ErrorMensaje guardarVendedor(Vendedor vendedor) {
		Vendedor vendedorNuevo = null;
		if(vendedor.getVendedor_id() != null) {
			vendedorNuevo = vendedorRepo.findById(vendedor.getVendedor_id())
					.orElseThrow(()-> new NotFoundException("No se encontro el vendedor"));
		}else {
			vendedorNuevo = new Vendedor();
		}
		
		vendedorNuevo.setNombres(vendedor.getNombres());
		vendedorNuevo.setApellidos(vendedor.getApellidos());
		vendedorNuevo.setDireccion(vendedor.getDireccion());
		vendedorNuevo.setTelefono(vendedor.getTelefono());
		vendedorNuevo.setCedula(vendedor.getCedula());
		vendedorNuevo.setEmail(vendedor.getEmail());
		try {
			vendedorRepo.save(vendedorNuevo);
			return new ErrorMensaje(false, "");
		} catch (Exception e) {
			return new ErrorMensaje(true, "El vendedor con la cedula " + vendedor.getCedula() + " Ya existe.");
		}
	
	}

	@Override
	public Vendedor obtenerVendedorPorId(Integer id) {
		
		return vendedorRepo.findById(id)
				.orElseThrow(()-> new NotFoundException("No se encontro el vendedor"));
	}

	@Transactional
	@Override
	public void eliminarVendedor(Integer id) {
		vendedorRepo.deactivate(id);
		
	}

	@Override
	public List<Vendedor> obtenerVendedoresActivos() {
		return vendedorRepo.findByEstadoTrue();
	}

}
