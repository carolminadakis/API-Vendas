package br.com.api.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.vendas.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
