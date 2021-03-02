package br.com.api.vendas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Item_Pedido")
public class ItemPedido {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer id;
	
	//Relação ManyToOne, pois eu posso ter muitos itens pedidos para 1 pedido
	@ManyToOne
	@JoinColumn (name = "id_pedido")
	private Pedido pedido;
	
	//Relação ManyToOne, pois eu posso ter muitos itens pedidos para 1 pedido
	@ManyToOne
	@JoinColumn (name = "id_produto")
	private Produto produto;
	private Integer quantidade;

}
