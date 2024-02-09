package com.lujos_occdente.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_cond_pago")
public class CondicionPago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cond_pago_id;
	private String nombre;
	
	public CondicionPago() {
		super();
	}

	public Integer getCond_pago_id() {
		return cond_pago_id;
	}

	public void setCond_pago_id(Integer cond_pago_id) {
		this.cond_pago_id = cond_pago_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CondicionPago [nombre=" + nombre + "]";
	}
	
	

}
