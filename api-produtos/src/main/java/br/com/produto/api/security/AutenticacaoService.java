package br.com.glandata.api.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.glandata.api.model.Usuario;
import br.com.glandata.api.repository.UsuarioRepository;

@Service
@Transactional
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = userRepository.findByLoginIgnoreCase(login);
		if (usuario.isPresent()) {
            return usuario.get();
        }
		throw new UsernameNotFoundException("Dados inválidos");
	}

}
