package com.lujos_occdente.servicios.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.entidades.Visita;
import com.lujos_occdente.repositorios.VisitaRepository;
import com.lujos_occdente.security.Usuario;
import com.lujos_occdente.servicios.UsuarioService;
import com.lujos_occdente.servicios.VisitaService;

@Service
public class VisitaServiceImpl implements VisitaService{
	
	@Autowired
	private VisitaRepository visitaRepo;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public List<Visita> obtenerVisitas(Usuario usuario) {
		String rolName = usuario.getRol().getRole_name();
		if(rolName.equals("ROLE_ADMIN")) {
			System.out.println("es admin");
			return visitaRepo.findAll();			
		}
		return obtenerVisitasPorVendedor(usuario.getVendedor());
	}

	@Override
	public ErrorMensaje guardarVisita(Visita visita) {
		Visita visitaNueva = null;
		if(visita.getId() != null) {
			visitaNueva = visitaRepo.findById(visita.getId())
					.orElseThrow(()-> new NotFoundException("Visita no encontrada"));
		}else {
			visitaNueva = new Visita();
		}
		if(visita.getVendedor() != null) {
			visitaNueva.setVendedor(visita.getVendedor());			
		}else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
			visitaNueva.setVendedor(usuario.getVendedor());
		}
		
		visitaNueva.setContacto(visita.getContacto());
		visitaNueva.setResultado(visita.getResultado());
		visitaNueva.setFecha(visita.getFecha());
		visitaNueva.setUbicacion(visita.getUbicacion());
		visitaNueva.setLatitud(visita.getLatitud());
		visitaNueva.setLongitud(visita.getLongitud());
		visitaNueva.setCliente(visita.getCliente());
		visitaNueva.setUbicacion(visita.getUbicacion());
		
		try {
			visitaRepo.save(visitaNueva);
			return new ErrorMensaje(false, "Visita guardada exitosamente.");
		} catch(Exception e) {
			return new ErrorMensaje(true, "Error guardando la visita.");
		}
		
	}

	@Override
	public void eliminarVisita(Integer id) {
		visitaRepo.deleteById(id);
		
	}

	@Override
	public List<Visita> obtenerVisitasPorVendedor(Vendedor vendedor) {
		return visitaRepo.findByVendedor(vendedor);
	}

	@Override
	public Visita obtenerVisitaPorId(Integer idVisita) {
		return visitaRepo.findById(idVisita)
				.orElseThrow(()-> new NotFoundException("No se encontro la visita con Id: " + idVisita));
	}

	@Override
	public List<Visita> obtenerVisitasFiltradasPorKeyword(String keyword) {
		return visitaRepo.findVisitasFilterByKeyword(keyword);
	}

	@Override
	public List<Visita> buscarVisitas(String keyword, Date fechaInicial, Date fechaFinal) {
		
		if( keyword != null && !keyword.isEmpty() && fechaInicial != null && fechaFinal != null) {
			System.out.println("todos los parametros");
			return visitaRepo.findByClienteRazonSocialContainingOrVendedorNombresContainingAndFechaBetween(keyword, keyword, fechaInicial, fechaFinal);
		}else if(keyword != null && !keyword.isEmpty()) {
			System.out.println("busqueda keyword");
			return visitaRepo.findByClienteRazonSocialContainingOrVendedorNombresContaining(keyword, keyword);
		}else if(fechaInicial != null) {
			System.out.println("Fecha inicial ");
			if(fechaFinal != null) {
				return visitaRepo.findByFechaBetween(fechaInicial, fechaFinal);
			}
			Date fechaActual = new Date();
			return visitaRepo.findByFechaBetween(fechaInicial, fechaActual);
		}
		return visitaRepo.findAll();
	}

	@Override
	public List<Visita> buscarVisitasPorVendedor(String nombreCliente, Date fechaInicial, Date fechaFinal,
			Vendedor vendedor) {		
		if(nombreCliente != null && fechaInicial != null && fechaFinal != null) {
			return visitaRepo.findByVendedorNombresContainingAndClienteRazonSocialContainingAndFechaBetween(vendedor.getNombres(), nombreCliente, fechaInicial, fechaFinal);
		}else if(nombreCliente != null && !nombreCliente.isEmpty()) {
			System.out.println("Palabra es: " + nombreCliente);
			return visitaRepo.findByVendedorAndClienteRazonSocialContaining(vendedor, nombreCliente);			
		}
		return visitaRepo.findByVendedor(vendedor);
	}


}


