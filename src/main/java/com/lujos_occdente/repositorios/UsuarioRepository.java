package com.lujos_occdente.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lujos_occdente.security.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByEstadoTrue();

	@Query("SELECT c FROM Usuario c WHERE c.nombre_usuario =:username AND estado = true")
	Usuario findByNombreUsuarioAndEstadoTrue(String username);

}
