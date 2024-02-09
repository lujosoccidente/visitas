package com.lujos_occdente.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/login")
	public String iniciarSesion() {

		return "login";
	}

	@GetMapping("/")
	public String mostrarInicio() {
		return "index";
	}
}
