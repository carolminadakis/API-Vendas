package br.com.api.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.vendas.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
