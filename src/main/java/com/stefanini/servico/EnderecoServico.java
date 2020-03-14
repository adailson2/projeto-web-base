package com.stefanini.servico;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.dao.PessoaDao;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.exceptions.ObjectNotFoundException;
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

		// Verifica se o endereço está sendo criado sem uma pessoa vinculada
		List<Pessoa> pessoas = pessoaDao.getList().get();
		if(entity.getIdPessoa() == null){
			throw new BadRequestException("Não é possível atualizar endereço sem pessoa!");
		}

		// Verifica se a pessoa existe no banco e atualiza o endereço
		Endereco endereco = null;
		for (Pessoa pessoa: pessoas) {
			if(pessoa.getId() == entity.getIdPessoa()){
				endereco = dao.atualizar(entity);
			}
		}

		if(endereco == null){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + entity.getIdPessoa()
					+ ", Tipo: " + Pessoa.class.getName());
		}

		return endereco;
	}

	/**
	 * Atualizar os dados de um Endereço
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco atualizar(@Valid Endereco entity) {

		// Verifica se objeto existe no banco
		Optional<Endereco> e = dao.encontrar(entity.getId());
		if(!e.isPresent()){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + entity.getId()
					+ ", Tipo: " + Endereco.class.getName());
		}

		// Verifica se o endereço está sendo criado sem uma pessoa vinculada
		List<Pessoa> pessoas = pessoaDao.getList().get();
		if(entity.getIdPessoa() == null){
			throw new BadRequestException("Não é possível atualizar endereço sem pessoa!");
		}

		// Verifica a relação com pessoa e atualiza o endereço caso tiver
		Endereco endereco = null;
		for (Pessoa pessoa: pessoas) {
			if(pessoa.getId() == entity.getIdPessoa()){
				endereco = dao.atualizar(entity);
			}
		}

		// Não foi encontrado a relação de pessoa e endereço
		if(endereco == null){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + entity.getIdPessoa()
					+ ", Tipo: " + Pessoa.class.getName());
		}

		return endereco;
	}

	/**
	 * Remover os dados de um Endereço
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
		Optional<Endereco> endereco = dao.encontrar(id);
		if(endereco.isPresent()){
			dao.remover(id);
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Endereco.class.getName());
		}
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
		Optional<Endereco> endereco = dao.encontrar(id);

		if(!endereco.isPresent()){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Endereco.class.getName());
		}

		return dao.encontrar(id);
	}
}
