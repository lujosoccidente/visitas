package com.lujos_occdente.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "submenu")
public class SubMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer submenu_id;
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;

	public SubMenu() {
		super();
	}

	public Integer getSubmenu_id() {
		return submenu_id;
	}

	public void setSubmenu_id(Integer submenu_id) {
		this.submenu_id = submenu_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "SubMenu [nombre=" + nombre + ", menu=" + menu + "]";
	}
	
	
}
