package br.com.api.vendas.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "id_pedido")
	private Integer id;
	
	//O relacionamento é ManyToOne pois vários pedidos podem ser atribuídos a 1 cliente.
	@ManyToOne
	@JoinColumn (name = "id_cliente")
	private Cliente cliente;
	
	@Column (name = "data_pedido")
	private LocalDate dataPedido;
	@Column (name = "total", precision = 20, scale = 2)
	private BigDecimal total;
	
	@OneToMany(mappedBy = "pedido") //deve ser usado entre as aspas o nome da propriedade que representa Pedido na classe ItemPedido
	private List<ItemPedido> itens;
	
}
