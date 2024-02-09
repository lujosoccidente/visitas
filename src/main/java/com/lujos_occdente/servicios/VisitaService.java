package com.lujos_occdente.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.entidades.Visita;
import com.lujos_occdente.security.Usuario;

public interface VisitaService {

	List<Visita> obtenerVisitas(Usuario usuario);

	ErrorMensaje guardarVisita(Visita visita);

	void eliminarVisita(Integer id);

	List<Visita> obtenerVisitasPorVendedor(Vendedor vendedor);

	Visita obtenerVisitaPorId(Integer idVisita);

	List<Visita> obtenerVisitasFiltradasPorKeyword(String keyword);

	List<Visita> buscarVisitas(String keyword, Date fechaInicial, Date fechaFinal);

	List<Visita> buscarVisitasPorVendedor(String keyword, Date fechaInicial, Date fechaFinal,
			Vendedor vendedor);

}
