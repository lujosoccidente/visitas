package com.lujos_occdente.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lujos_occdente.repositorios.UsuarioRepository;

@Service
@Transactional
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByNombreUsuarioAndEstadoTrue(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
		return new Principal(usuario.getNombre_usuario(), usuario.getContrasena(), mapearRoles(usuario.getRol()), (usuario.getNombres() + " " + usuario.getApellidos()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Rol rol) {
		return getGrantedAuthorities(getPrivileges(rol));
	}

	private List<String> getPrivileges(Rol rol) {
		List<String> privileges = new ArrayList<>();
		
		privileges.add(rol.getRole_name());
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
		return authorities;
	}


}
