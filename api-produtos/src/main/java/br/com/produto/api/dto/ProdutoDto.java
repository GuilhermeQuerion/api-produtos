package br.com.glandata.api.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.glandata.api.model.Produto;
import lombok.Getter;

public class ProdutoDto {
	
	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
	}

	@Getter
	private String nome;

	@Getter
	private String descricao;

	@Getter
	private BigDecimal preco;
	
	public static List<ProdutoDto> converte(List<Produto> produtos){
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
	public static Page<ProdutoDto> converteToPage(Page<Produto> produtos){
		return produtos.map(ProdutoDto::new);
	}
}
