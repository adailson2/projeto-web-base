package com.stefanini.servico;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.model.Endereco;
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
public class EnderecoServico implements Serializable {

	/**
	 * Serializacao da Classe
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecoDao dao;

	/**
	 * Salvar os dados de um Endereço
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco salvar(@Valid Endereco entity) {
		return dao.salvar(entity);
	}

	/**
	 * Atualizar os dados de um Endereço
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco atualizar(@Valid Endereco entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover os dados de um Endereço
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
		dao.remover(id);
	}

	/**
	 * Buscar uma lista de Endereços
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<List<Endereco>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar um Endereço por ID
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<Endereco> encontrar(Long id) {
		return dao.encontrar(id);
	}
}
