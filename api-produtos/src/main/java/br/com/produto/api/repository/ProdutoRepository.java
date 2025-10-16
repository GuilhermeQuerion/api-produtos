package br.com.glandata.api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.glandata.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findByCategoriaId(Long idCategoria);
	
	List<Produto> findByNomeContaining(String nome);
	
	List<Produto> findByNomeContainingIgnoreCase(String nome);
	
	@Query("SELECT p FROM Produto p where p.nome = :nome")
	List<Produto> listaTodosPorNome(@Param("nome") String nome);
	
	@Query(value= "SELECT * FROM produtos p WHERE p.preco >= :preco", nativeQuery = true)
	List<Produto> listarProdutosPrecoMaiorQue(@Param("preco") BigDecimal preco);

	
}
