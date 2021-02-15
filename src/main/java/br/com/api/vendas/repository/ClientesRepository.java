package br.com.api.vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.vendas.model.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNome (String nome);
	
	//o exists vai fazer a pesquisa por nome e retornar um boolean
	boolean existsByNome (String nome);
	
	@Query ("select c from Cliente c left join fetch c.pedido where c.id =:id")
	Cliente findClienteFetchPedidos (@Param("id") Integer id);
	
	
}
