package com.lujos_occdente.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lujos_occdente.entidades.TiposPermisos;

@Entity
@Table(name = "permiso")
public class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer permiso_id;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name = "submenu_id")
	private SubMenu submenu;
	
	@ManyToOne
	@JoinColumn(name = "m_tipos_permisos_id")
	private TiposPermisos tipoPermiso;
	
	@ManyToOne
	@JoinColumn(name = "rol_id")
	private Rol rol;
	
	private Boolean estado;

	public Permiso() {
		super();
	}

	public Integer getPermiso_id() {
		return permiso_id;
	}

	public void setPermiso_id(Integer permiso_id) {
		this.permiso_id = permiso_id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public SubMenu getSubmenu() {
		return submenu;
	}

	public void setSubmenu(SubMenu submenu) {
		this.submenu = submenu;
	}

	public TiposPermisos getTipoPermiso() {
		return tipoPermiso;
	}

	public void setTipoPermiso(TiposPermisos tipoPermiso) {
		this.tipoPermiso = tipoPermiso;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
	
}
