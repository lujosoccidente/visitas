package com.lujos_occdente.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "clientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "nit" }) })
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cliente_id;
	private String nit;
	@Column(name = "razon_social")
	private String razonSocial;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String email;
	private Boolean ind_estado_bloqueado=true;
	@Column(name = "ind_estado")
	private Boolean estado=true;
	
	@OneToOne
	@JoinColumn(name="cond_pago_id")
	private CondicionPago cond_pago;
	
	@OneToOne
	@JoinColumn(name="tipo_cliente_id")
	private TipoCliente tipo_cliente;
	
	@OneToOne
	@JoinColumn(name="vendedor_id")
	private Vendedor vendedor;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contacto> contactos = new ArrayList<>(); 

	public Cliente() {
		super();
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razon_social) {
		this.razonSocial = razon_social;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getInd_estado_bloqueado() {
		return ind_estado_bloqueado;
	}

	public void setInd_estado_bloqueado(Boolean ind_estado_bloqueado) {
		this.ind_estado_bloqueado = ind_estado_bloqueado;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean ind_estado) {
		this.estado = ind_estado;
	}	

	public CondicionPago getCond_pago() {
		return cond_pago;
	}

	public void setCond_pago(CondicionPago cond_pago) {
		this.cond_pago = cond_pago;
	}

	public TipoCliente getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(TipoCliente tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
        contacto.setCliente(this);
    }

	@Override
	public String toString() {
		return "Cliente [cliente_id=" + cliente_id + ", nit=" + nit + ", razon_social=" + razonSocial + ", nombres="
				+ nombres + ", apellidos=" + apellidos + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", email=" + email + ", ind_estado_bloqueado=" + ind_estado_bloqueado + ", ind_estado=" + estado
				+ ", cond_pago=" + cond_pago + ", tipo_cliente=" + tipo_cliente + ", contactos=" + contactos + "]";
	}

	
	
	
	
	

}
