package com.lujos_occdente.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_tipos_permisos")
public class TiposPermisos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer m_tipos_permisos_id;
	private String nombre;
	
	public TiposPermisos() {
		super();
	}

	public Integer getM_tipos_permisos_id() {
		return m_tipos_permisos_id;
	}

	public void setM_tipos_permisos_id(Integer m_tipos_permisos_id) {
		this.m_tipos_permisos_id = m_tipos_permisos_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TiposPermisos [m_tipos_permisos_id=" + m_tipos_permisos_id + ", nombre=" + nombre + "]";
	}
	
	
	
}
