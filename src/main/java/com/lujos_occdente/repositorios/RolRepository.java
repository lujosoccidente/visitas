package com.lujos_occdente.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lujos_occdente.security.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

	List<Rol> findByEstadoTrue();

}
