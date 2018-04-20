package br.com.alex.info.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alex.info.model.Vinho;

public interface VinhosRepository extends JpaRepository<Vinho, Long>{
	
	public List<Vinho> findByNomeContainingIgnoreCase(String nome);

}
