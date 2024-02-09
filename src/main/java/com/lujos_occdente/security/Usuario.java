package com.lujos_occdente.security;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lujos_occdente.entidades.Vendedor;

@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre_usuario" }) })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuario_id;
	private String nombre_usuario;
	private String contrasena;	
	private String nombres;	
	private String apellidos;	
	private String cedula;	
	private String email;	
	private Boolean estado=true;
	
	@ManyToOne()
    @JoinColumn(name="role_id", referencedColumnName = "role_id")
    private Rol rol;
	
	@OneToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "vendedor_id")
    private Vendedor vendedor;
	
	public Usuario() {
		super();
	}

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	@Override
	public String toString() {
		return "Usuario [usuario_id=" + usuario_id + ", nombre_usuario=" + nombre_usuario + ", contrasena=" + contrasena
				+ ", nombres=" + nombres + ", apellidos=" + apellidos + ", cedula=" + cedula + ", email=" + email
				+ ", estado=" + estado + ", rol=" + rol + ", vendedor=" + vendedor.getNombres() + "]";
	}



	
	
	
	
}
