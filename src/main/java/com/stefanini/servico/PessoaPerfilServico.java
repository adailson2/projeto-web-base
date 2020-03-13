package com.stefanini.servico;

import com.stefanini.dao.PessoaPerfilDao;
import com.stefanini.model.PessoaPerfil;

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
public class PessoaPerfilServico implements Serializable {

	/**
	 * Serializacao da Classe
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaPerfilDao dao;

	/**
	 * Vincular Pessoa e Perfil
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfil salvar(@Valid PessoaPerfil pessoaPerfil) {
		return dao.salvar(pessoaPerfil);
	}

	/**
	 * Alterar vínculo entre Pessoa e Perfil
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfil atualizar(@Valid PessoaPerfil entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover vínculo entre Pessoa e Perfil
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) {
		dao.remover(id);
	}

	/**
	 * Buscar a lista de relação entre os registros vinculados
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<List<PessoaPerfil>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar uma PessoaPerfil pelo ID
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<PessoaPerfil> encontrar(Long id) {
		return dao.encontrar(id);
	}

}
