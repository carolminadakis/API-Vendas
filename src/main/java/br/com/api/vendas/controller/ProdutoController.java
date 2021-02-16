package br.com.api.vendas.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.api.vendas.model.Produto;
import br.com.api.vendas.repository.ProdutosRepository;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutosRepository pr;
	
	@GetMapping("/{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return pr.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@PostMapping
	@Transactional
	@ResponseStatus(CREATED)
	public Produto save(@Valid @RequestBody Produto produto) {
		return pr.save(produto);
	}
	
	@DeleteMapping("{/id}")
	@ResponseStatus(NO_CONTENT)
	@Transactional
	public void delete(@PathVariable Integer id) {
		pr.findById(id)
		.map(produtoExistente -> { pr.delete(produtoExistente);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@PutMapping("{/id}")
	@ResponseStatus(NO_CONTENT)
	@Transactional
	public void update(@PathVariable Integer id, @Valid @RequestBody Produto produto) {
		pr.findById(id)
		.map(produtoExistente -> { produto.setId(produtoExistente.getId());
		pr.save(produto);
		return produto;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}
	
	@GetMapping
	public List<Produto> findProduto(Produto filtroProduto) {
		ExampleMatcher matcher = ExampleMatcher.matching()
																.withIgnoreCase()
																.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example example = Example.of(filtroProduto, matcher);
		return pr.findAll(example);
	}
}
