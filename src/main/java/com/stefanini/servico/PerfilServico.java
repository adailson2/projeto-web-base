package com.stefanini.servico;

import com.stefanini.dao.PerfilDao;
import com.stefanini.model.Perfil;
import com.stefanini.util.IGenericService;

import javax.ejb.*;
import javax.inject.Inject;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PerfilServico implements Serializable {

	/**
	 * Serializacao da Classe
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PerfilDao dao;

	/**
	 * Salvar os dados de um Perfil
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil salvar(@Valid Perfil entity) {
		return dao.salvar(entity);
	}

	/**
	 * Atualizar os dados de um Perfil
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil atualizar(@Valid Perfil entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover os dados de um Perfil
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
		dao.remover(id);
	}

	/**
	 * Buscar uma lista de Perfis
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<List<Perfil>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar um Perfil por ID
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<Perfil> encontrar(Long id) {
		return dao.encontrar(id);
	}
}
