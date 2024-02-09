package com.lujos_occdente.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_tipos_cliente")
public class TipoCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tipo_cliente_id;
	private String nombre; 
	
	public TipoCliente() {
		super();
	}

	public Integer getTipo_cliente_id() {
		return tipo_cliente_id;
	}

	public void setTipo_cliente_id(Integer tipo_cliente_id) {
		this.tipo_cliente_id = tipo_cliente_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoCliente [nombre=" + nombre + "]";
	}
	
	

}
