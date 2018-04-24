package br.com.alex.info.services;

import java.util.List;

import br.com.alex.info.model.Vinho;
import br.com.alex.info.repository.filter.VinhoFilter;

public interface VinhoService {
	
	public Vinho salvar(Vinho vinho);
	public void excluir(Long id);
	public Vinho buscarPorId(Long id);
	public List<Vinho> buscarPorNome(VinhoFilter vinhoFilter);	

}
