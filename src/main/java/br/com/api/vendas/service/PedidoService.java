package br.com.api.vendas.service;

import br.com.api.vendas.dto.PedidoDto;
import br.com.api.vendas.model.Pedido;

public interface PedidoService {

	Pedido salvar(PedidoDto dto);
}
