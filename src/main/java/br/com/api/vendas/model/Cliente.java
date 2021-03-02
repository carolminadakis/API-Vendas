package br.com.api.vendas.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "id_cliente")
	private Integer id;
	@Column (name = "nome_cliente", length = 100)
	@NotEmpty @NotNull
	private String nome;
	@Column (name = "cpf_cliente", length = 14)
	private String cpf;
	
	//O relacionamento é OneToMany pois 1 Cliente pode ter vários pedidos. 
	@OneToMany (mappedBy = "cliente") //deve ser usado entre as aspas o nome da propriedade que representa Cliente na classe Pedido.
	private Set<Pedido> pedidos;
	
}
