package com.stefanini.servico;

import com.stefanini.dao.PerfilDao;
import com.stefanini.model.Perfil;
import com.stefanini.util.IGenericService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * @author joaopedromilhome
 *
 */
public class PerfilServico implements IGenericService<Perfil, Long> {
	
	@Inject
	private PerfilDao dao;

	/**
	 * Salvar os dados de um Perfil
	 */
	@Override
	public Perfil salvar(@Valid Perfil entity) {
		return dao.salvar(entity);
	}

	/**
	 * Atualizar os dados de um Perfil
	 */
	@Override
	public Perfil atualizar(@Valid Perfil entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover os dados de um Perfil
	 */
	@Override
	public void remover(Long id) {
		dao.remover(id);
	}

	/**
	 * Buscar uma lista de Perfis
	 */
	@Override
	public Optional<List<Perfil>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar um Perfil por ID
	 */
	@Override
	public Optional<Perfil> encontrar(Long id) {
		return dao.encontrar(id);
	}
}
