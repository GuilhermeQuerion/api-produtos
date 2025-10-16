package br.com.glandata.api.validacao;

import lombok.Getter;

public class ErroDeFormularioDto {
	
	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	
	@Getter
	private String campo;
	
	@Getter
	private String erro;
	
	

}
