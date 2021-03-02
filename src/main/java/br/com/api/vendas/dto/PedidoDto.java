package br.com.api.vendas.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDto> items;
	
}
