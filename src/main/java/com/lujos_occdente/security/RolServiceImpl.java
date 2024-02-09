package com.lujos_occdente.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lujos_occdente.repositorios.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepo;
	@Override
	public List<Rol> obtenerRolesActivos() {
		return rolRepo.findByEstadoTrue();
	}

}
