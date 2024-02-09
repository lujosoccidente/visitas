package com.lujos_occdente.entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "visitas")
public class Visita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visita_id")
	private Integer id;
	private String contacto;
	private String resultado;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "fecha_visita")
	private Date fecha;
	private String ubicacion;
	private Float latitud;
	private Float longitud;
	
	@ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Visita() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer visita_id) {
		this.id = visita_id;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha_visita) {
		this.fecha = fecha_visita;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float lonitud) {
		this.longitud = lonitud;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Visita [visita_id=" + id + ", contacto=" + contacto + ", resultado=" + resultado
				+ ", fecha_visita=" + fecha + ", ubicacion=" + ubicacion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", vendedor=" + vendedor + ", cliente=" + cliente + "]";
	}
	
	
}
