package com.lujos_occdente.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lujos_occdente.entidades.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

	List<Vendedor> findByEstadoTrue();

	@Modifying
	@Query(value = "UPDATE vendedores "
			+ "SET estado = 0 "
			+ "WHERE vendedor_id = :id ", nativeQuery = true)
	void deactivate(@Param("id") Integer id);

	List<Vendedor> findByNombresContainingAndEstadoTrue(String nombres);

}
