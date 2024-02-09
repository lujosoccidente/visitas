package com.lujos_occdente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LujosDeOccidenteApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LujosDeOccidenteApplication.class, args);
		// LujosDeOccidenteApplication app = context.getBean(LujosDeOccidenteApplication.class);
		
		// app.run();
		
        
		//SpringApplication.run(LujosDeOccidenteApplication.class, args);
	}
	
	private void run() {
		String rawPassword = "admin";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Encriptar la contraseña
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Contraseña encriptada: " + encryptedPassword);
	}
	

}
