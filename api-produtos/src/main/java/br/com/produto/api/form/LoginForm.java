package br.com.glandata.api.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

public class LoginForm {

	@Getter @Setter
	private String login;
	
	@Getter @Setter
	private String senha;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, senha);	
	}
	
}
