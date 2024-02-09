package com.lujos_occdente.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lujos_occdente.security.Usuario;

@Entity
@Table(name = "vendedores", uniqueConstraints = { @UniqueConstraint(columnNames = { "cedula" })})
public class Vendedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vendedor_id;
	private String nombres;	
	private String apellidos;	
	private String direccion;
	private String telefono;
	private String cedula;	
	private String email;
	private Boolean estado=true;
	
	
	@OneToOne(mappedBy = "vendedor")
    private Usuario usuario;
	
	public Vendedor() {
		super();
	}

	public Integer getVendedor_id() {
		return vendedor_id;
	}

	public void setVendedor_id(Integer vendedor_id) {
		this.vendedor_id = vendedor_id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
