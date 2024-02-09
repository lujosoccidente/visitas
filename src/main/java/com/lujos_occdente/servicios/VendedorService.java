package com.lujos_occdente.servicios;

import java.util.List;


import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Vendedor;


public interface VendedorService {

	List<Vendedor> obtenerVendedores(String keyword);

	ErrorMensaje guardarVendedor(Vendedor vendedor);

	Vendedor obtenerVendedorPorId(Integer id);

	void eliminarVendedor(Integer id);

	List<Vendedor> obtenerVendedoresActivos();

}
