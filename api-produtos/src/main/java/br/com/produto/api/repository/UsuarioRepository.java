package br.com.glandata.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.glandata.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	//Usuario findByLoginIgnoreCase(String login);
	Optional<Usuario> findByLoginIgnoreCase(String login);

}
