package br.com.glandata.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glandata.api.dto.ProdutoDto;
import br.com.glandata.api.model.Produto;
import br.com.glandata.api.repository.ProdutoRepository;

@RestController
@RequestMapping(path="/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/**
	 * Lista todos os produtos cadastrados com as informações completas
	 * @return lista de produtos
	 */
	/*
	@GetMapping
	public ResponseEntity<List<Produto>> listAll(){
		List<Produto> produtos = new ArrayList<>();
		produtos = produtoRepository.findAll();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	*/
	
	/**
	 * Lista todos os produtos cadastrados restringindo via DTO
	 * as informações que serão exibidas
	 * @return Lista de produtosDto
	 */
	/*
	@GetMapping
	public ResponseEntity<List<ProdutoDto>> listAllDto(){
		List<Produto> produtos = new ArrayList<>();
		produtos = produtoRepository.findAll();
		return ResponseEntity.ok(ProdutoDto.converte(produtos));
	}
	*/
	@GetMapping
	@Cacheable(value = "listaProdutos")
	public ResponseEntity<Page<Produto>> listarComPaginacao(Pageable pageable){
				Page<Produto> produtos = produtoRepository.findAll(pageable);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	/**
	 * Lista todos os produtos cadastrados a partir de uma categoria
	 * restringindo via DTO as informações que serão exibidas dos objetos
	 * @param id recebe o id da categoria
	 * @return Lista de produtosDto por categoria indicada
	 */
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<ProdutoDto>> listAllByCategoria(@PathVariable Long id){
		List<Produto> produtos = new ArrayList<>();
		produtos = produtoRepository.findByCategoriaId(id);
		return ResponseEntity.ok(ProdutoDto.converte(produtos));
	}
	
	/**
	 * Cadastrar uma novo produto
	 * @param produto recebe as informações do objeto vindo no corpo da requisição em formato json
	 * @return objeto produto em formato json + http status 200
	 */
	@PostMapping
	@CacheEvict(value = "listaProdutos")
	public ResponseEntity<Produto> create(@Valid @RequestBody Produto produto){
		produtoRepository.save(produto);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}
	
	/**
	 * Busca um produto pelo ID
	 * @param id recebe o id do produto a ser buscado
	 * @return caso encontre o registro retorna o objeto em formato json junto do http status 200,
	 * caso não encontre retorna status 404 Not Found
	 */
	@GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
        return produto
        		.map(prod -> ResponseEntity.ok().body(prod))
        		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
	/**
	 * Busca um produto pelo nome
	 * @param q recebe a chave de busca (q = query), a chave de busca é case insensitive
	 * e o registro pode se localizado apenas por conter parte da chave
	 * @return Lista de produtosDto de acordo com a chave de busca
	 */
	@GetMapping("/buscarPorNome")
	public ResponseEntity<List<ProdutoDto>> listByNome(String q){
		List<Produto> produtos = new ArrayList<>();
		produtos = produtoRepository.findByNomeContainingIgnoreCase(q);
		return ResponseEntity.ok(ProdutoDto.converte(produtos));
	}
	
	
	/**
	 * Lista os produtos por preço maior que valor informado na chave
	 * @param q recebe a chave de busca (q = query) 
	 * @return Lista de produtosDto com preço maior que valor informado na chave
	 */
	@GetMapping("/buscarPorPrecoMaiorQue")
	public ResponseEntity<List<ProdutoDto>> listByPreco(@NotBlank BigDecimal q){
		List<Produto> produtos = new ArrayList<>();
		try {
			produtos = produtoRepository.listarProdutosPrecoMaiorQue(q);
			return ResponseEntity.ok(ProdutoDto.converte(produtos));	
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	/**
	 * Busca um produto por id e atualiza as informações
	 * @param id recebe o id do produto a ser atualizado
	 * @param produto recebe as informações do objeto vindo no corpo da requisição em formato json 
	 * @return caso o produto seja atualizado corretamente retorna o objeto em formato json +
	 *  http status 200, caso não seja encontrado/atualizado retorna apenas status 404 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoRepository.findById(id).map(prod -> {
			
			prod.setNome(produto.getNome());
			prod.setDescricao(produto.getDescricao());
			prod.setPreco(produto.getPreco());
			
			Produto produtoAtualizado = produtoRepository.save(prod);
			return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizado);
			
		}).orElse(ResponseEntity.notFound().build());
	}
	
	
	/**
	 * Busca um produto por id e deleta o registro
	 * @param id recebe o id do produto que será deletado
	 * @return caso o produto seja encontrado deleta o registro e retorna http status 200, 
	 * caso não seja encontrado retorna apenas status 404 
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(prod -> {
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
}

