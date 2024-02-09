package com.lujos_occdente.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lujos_occdente.entidades.TipoCliente;
import com.lujos_occdente.repositorios.TipoClienteRepository;
import com.lujos_occdente.servicios.TiposClienteService;

@Service
public class TiposClienteServiceImpl implements TiposClienteService {

	@Autowired
	private TipoClienteRepository tipoClienteRepo;
	@Override
	public List<TipoCliente> obtenerTodosTiposCliente() {
		
		return tipoClienteRepo.findAll();
	}

}
