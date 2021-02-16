package br.com.api.vendas.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.api.vendas.model.Cliente;
import br.com.api.vendas.repository.ClientesRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClientesRepository cr;
	
//Encontrando cliente por id:	
	@GetMapping("/{id}")
	public Cliente getClienteById (@PathVariable Integer id) {
		return cr.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
												"Cliente não encontrado")); 
	}
	
//Salvando cliente
	@PostMapping
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save (@RequestBody @Valid Cliente cliente) {
		return cr.save(cliente);
	}
	
//Deletando cliente
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	public void delete(@PathVariable Integer id) {
		cr.findById(id)
		.map(cliente -> { cr.delete(cliente);
			return cliente;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")); 
	}
	
//Atualizando dados do cliente
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	public void update(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
		 cr.findById(id)
				.map(clienteExistente -> {cliente.setId(clienteExistente.getId());
				cr.save(cliente);
				return cliente;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Cliente não encontrado")); 
	}
	
	@GetMapping
	public List<Cliente> findCliente (Cliente filtroCliente) {
//O Example irá buscar no repository o cliente contendo as informações de acordo com as especificações passadas na requisição
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()     //ignora a escrita em caixa alta ou baixa, trazendo todos os possíveis resultados
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);       //com o CONTAINING o Example valida exatamente as informações que foram passadas na requisição
		
		Example example = Example.of(filtroCliente, matcher);  //faz a validação considerando as atribuições da requisição, comparando com o matcher
		return cr.findAll(example);    //lista os resultados encontrados
	}
}
