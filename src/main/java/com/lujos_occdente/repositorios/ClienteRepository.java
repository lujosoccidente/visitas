package com.lujos_occdente.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lujos_occdente.entidades.Cliente;
import com.lujos_occdente.entidades.Vendedor;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByEstadoTrue();
	
	@Modifying
	@Query(value="UPDATE clientes "
			+ "SET ind_estado = 0 "
			+ "WHERE cliente_id = :id ", nativeQuery = true)
	void deactivate(@Param("id") Integer id);

	List<Cliente> findByVendedor(Vendedor vendedor);

	List<Cliente> findByRazonSocialContainingAndEstadoTrue(String nombre);

}