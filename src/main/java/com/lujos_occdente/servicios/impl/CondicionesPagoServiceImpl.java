package com.lujos_occdente.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lujos_occdente.entidades.CondicionPago;
import com.lujos_occdente.repositorios.CondicionPagoRepository;
import com.lujos_occdente.servicios.CondicionesPagoService;

@Service
public class CondicionesPagoServiceImpl implements CondicionesPagoService {

	@Autowired
	private CondicionPagoRepository condicionesPagoRepo;
	@Override
	public List<CondicionPago> obtenerCondicionesPago() {
		return condicionesPagoRepo.findAll();
	}

}
