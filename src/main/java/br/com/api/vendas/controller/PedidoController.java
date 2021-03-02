package br.com.api.vendas.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.api.vendas.dto.PedidoDto;
import br.com.api.vendas.model.Pedido;
import br.com.api.vendas.service.PedidoService;

public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save(@RequestBody PedidoDto dto) {
		Pedido pedido = pedidoService.salvar(dto);
		return pedido.getId();
	}
}
