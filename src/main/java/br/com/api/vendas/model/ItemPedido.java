package br.com.api.vendas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
