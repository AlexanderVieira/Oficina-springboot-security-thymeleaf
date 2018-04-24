package br.com.alex.info.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alex.info.model.Vinho;
import br.com.alex.info.repository.VinhosRepository;
import br.com.alex.info.repository.filter.VinhoFilter;
import br.com.alex.info.services.VinhoService;

@Service
public class VinhoServiceImpl implements VinhoService {
	
	@Autowired
	private VinhosRepository vinhosRepo;

	@Override
	public Vinho salvar(Vinho vinho) {
		vinhosRepo.save(vinho);
		return vinho;
	}

	@Override
	public void excluir(Long id) {
		vinhosRepo.delete(id);

	}

	@Override
	public Vinho buscarPorId(Long id) {
		return this.vinhosRepo.findOne(id);		
	}

	@Override
	public List<Vinho> buscarPorNome(VinhoFilter vinhoFilter) {
		return vinhosRepo.findByNomeContainingIgnoreCase(Optional
				.ofNullable(vinhoFilter.getNome()).orElse("%"));	
	}

}
