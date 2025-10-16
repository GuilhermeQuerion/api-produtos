package br.com.glandata.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produtos")
public class Produto {
	
	public Produto() {
	}
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter @Setter
	@NotBlank(message = "O nome não pode ser vazio")
	private String nome;

	@Getter @Setter
	@NotBlank(message = "A descrição não pode ser vazio")
	private String descricao;

	@Getter @Setter
	@Column(name="preco", columnDefinition = "Decimal(11,2) default '0.00'")
	@NotNull(message = "Informe o preço do produto")
	private BigDecimal preco;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "id_categoria", foreignKey = @ForeignKey(name="fk_categoria"))
	@NotNull(message = "Selecione uma categoria")
	private Categoria categoria;
	
	@Getter @Setter
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro = LocalDate.now();
	
	@Getter @Setter
	@NotBlank(message = "Selecione uma situação")
	private String situacao;

}

