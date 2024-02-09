package com.lujos_occdente.servicios;

import java.util.List;

import com.lujos_occdente.dtos.ErrorMensaje;
import com.lujos_occdente.entidades.Cliente;
import com.lujos_occdente.entidades.Vendedor;


public interface ClienteService {

	ErrorMensaje guardarCliente(Cliente cliente);

	void eliminarCliente(Integer id);

	List<Cliente> obtenerClientes(String keyword);

	Cliente obtenerClientePorId(Integer id);

	List<Cliente> obtenerClientesVendedor(Vendedor vendedor);

	List<Cliente> obtenerTodosClientesActivos();

}
