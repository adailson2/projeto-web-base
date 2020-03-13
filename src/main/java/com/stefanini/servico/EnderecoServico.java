package com.stefanini.servico;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.dao.PessoaDao;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;
import com.stefanini.util.IGenericService;

import javax.ejb.*;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
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

	@Inject
	private PessoaDao pessoaDao;

	/**
	 * Salvar os dados de um Endereço
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco salvar(@Valid Endereco entity) {
		List<Pessoa> pessoas = pessoaDao.getList().get();
		Endereco endereco = null;

		if(entity.getIdPessoa() == null){
			throw new BadRequestException("Não é possível cadastrar endereço sem pessoa!");
		}

		for (Pessoa pessoa: pessoas) {
			if(pessoa.getId() == entity.getIdPessoa()){
				endereco = dao.salvar(entity);
			}
		}

		if(endereco == null){
			throw new BadRequestException("Não foi possível encontrar a pessoa de ID: " + entity.getIdPessoa());
		}

		return endereco;
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
