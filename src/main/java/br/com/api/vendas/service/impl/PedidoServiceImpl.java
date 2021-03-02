package br.com.api.vendas.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.vendas.dto.ItemPedidoDto;
import br.com.api.vendas.dto.PedidoDto;
import br.com.api.vendas.exception.RegraDeNegocioException;
import br.com.api.vendas.model.Cliente;
import br.com.api.vendas.model.ItemPedido;
import br.com.api.vendas.model.Pedido;
import br.com.api.vendas.model.Produto;
import br.com.api.vendas.repository.ClientesRepository;
import br.com.api.vendas.repository.ItemPedidoRepository;
import br.com.api.vendas.repository.PedidoRepository;
import br.com.api.vendas.repository.ProdutosRepository;
import br.com.api.vendas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClientesRepository clienteRepository;
	@Autowired
	private ProdutosRepository produtosRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDto dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clienteRepository.findById(idCliente)
												   .orElseThrow( ()-> new RegraDeNegocioException("Código de cliente inválido!"));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		
		return pedido;
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDto> items) {
		if(items.isEmpty()) {
			throw new RegraDeNegocioException("Lista de pedidos vazia.");
		}
		return items.stream()
					   .map(dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtosRepository.findById(idProduto)
			     					.orElseThrow( ()-> new RegraDeNegocioException("Código de cliente inválido!" + idProduto));
			
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());
	}
}
