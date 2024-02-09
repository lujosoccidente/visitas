package com.lujos_occdente.servicios;

import java.util.List;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.security.Usuario;

public interface UsuarioService {
	
	public List<Usuario> obtenerUsuariosActivos();
	
	public Usuario obtenerUsuarioPorId(Integer id);
	
	public ErrorMensaje guardarUsuario(Usuario usuario);

	public Usuario ObtenerUsuarioPorNombreUsuario(String name);

}
