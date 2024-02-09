package com.lujos_occdente.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.repositorios.UsuarioRepository;
import com.lujos_occdente.security.Usuario;
import com.lujos_occdente.servicios.UsuarioService;
import com.lujos_occdente.servicios.VendedorService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VendedorService venderdorService;
	
	
	public List<Usuario> obtenerUsuariosActivos() {
		return usuarioRepo.findByEstadoTrue();
	}

	public Usuario obtenerUsuarioPorId(Integer id) {
		
		return usuarioRepo.findById(id)
				.orElseThrow(()-> new NotFoundException("No se encontro el usuario."));
	}
	

	public ErrorMensaje guardarUsuario(Usuario usuario) {
		Usuario usuarioNuevo = null;
		if(usuario.getUsuario_id() != null) {
			usuarioNuevo = usuarioRepo.findById(usuario.getUsuario_id())
					.orElseThrow(()-> new NotFoundException("No se encontro el usuario."));
		}else {
			usuarioNuevo = new Usuario();
		}
		
		usuarioNuevo.setNombre_usuario(usuario.getNombre_usuario());
		usuarioNuevo.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
		usuarioNuevo.setNombres(usuario.getNombres());
		usuarioNuevo.setApellidos(usuario.getApellidos());
		usuarioNuevo.setCedula(usuario.getCedula());
		usuarioNuevo.setEmail(usuario.getEmail());
		usuarioNuevo.setEstado(usuario.getEstado());
		usuarioNuevo.setRol(usuario.getRol());
		if(usuario.getVendedor() != null) {
			Vendedor vendedor = venderdorService.obtenerVendedorPorId(usuario.getVendedor().getVendedor_id());
			vendedor.setUsuario(usuarioNuevo);
			venderdorService.guardarVendedor(vendedor);
			usuarioNuevo.setVendedor(vendedor);
		}
		
		try {
			usuarioRepo.save(usuarioNuevo);
			return new ErrorMensaje(false, "");
		} catch (DataIntegrityViolationException e) {
			return new ErrorMensaje(true, "El nombre de usuario " + usuario.getNombre_usuario() + " ya existe.");
		}
		
	}

	@Override
	public Usuario ObtenerUsuarioPorNombreUsuario(String name) {
		return usuarioRepo.findByNombreUsuarioAndEstadoTrue(name);
	}
}
