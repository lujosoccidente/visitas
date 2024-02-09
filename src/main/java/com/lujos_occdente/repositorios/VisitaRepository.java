package com.lujos_occdente.repositorios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lujos_occdente.entidades.Vendedor;
import com.lujos_occdente.entidades.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Integer> {

	List<Visita> findByVendedor(Vendedor vendedor);

	Optional<Visita> findById(Integer idVisita);

	@Query(value = "SELECT * "
			+ "FROM visitas "
			+ "JOIN clientes "
			+ "ON clientes.cliente_id = visitas.cliente_id "
			+ "JOIN vendedores "
			+ "ON vendedores.vendedor_id= visitas.vendedor_id "
			+ "WHERE CONCAT(clientes.razon_social, vendedores.nombres) "
			+ "LIKE %:keyword% ", nativeQuery = true)
	List<Visita> findVisitasFilterByKeyword(@Param("keyword") String keyword);

	List<Visita> findByClienteRazonSocialContainingOrVendedorNombresContainingAndFechaBetween(String keyword, String keyword2,
			Date fechaInicial, Date fechaFinal);

	List<Visita> findByVendedorNombresContainingAndClienteRazonSocialContainingAndFechaBetween(String nombres,
			String keyword, Date fechaInicial, Date fechaFinal);

	List<Visita> findByVendedorAndClienteRazonSocialContaining(Vendedor vendedor, String nombreCliente);

	List<Visita> findByClienteRazonSocialContainingOrVendedorNombresContaining(String keyword, String keyword2);

	List<Visita> findByFechaBetween(Date fechaInicial, Date fechaActual);

}
