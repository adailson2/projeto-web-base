package com.stefanini.servico;

import com.stefanini.dao.PessoaDao;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;
import com.stefanini.servico.exceptions.EmailAlreadyUsedException;
import com.stefanini.servico.exceptions.ObjectNotFoundException;
import com.stefanini.util.IGenericService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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
public class PessoaServico implements Serializable {

	/**
	 * Serializacao da Classe
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaDao dao;

	/**
	 * Salvar os dados de uma Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa salvar(@Valid Pessoa pessoa) {
		Optional<List<Pessoa>> pessoas = dao.getList();
		for (Pessoa p: pessoas.get()) {
			if(p.getEmail().equals(pessoa.getEmail())) {
				throw new EmailAlreadyUsedException();
			}
		}
		return dao.salvar(pessoa);
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa atualizar(@Valid Pessoa pessoa) {
		// Verifica se objeto existe no banco
		Optional<Pessoa> p = dao.encontrar(pessoa.getId());
		if(!p.isPresent()){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + pessoa.getId()
					+ ", Tipo: " + Pessoa.class.getName());
		}

		// Verifica se o email já existe
		Optional<List<Pessoa>> pessoas = dao.getList();
		for (Pessoa pes: pessoas.get()) {
			if(pes.getEmail().equals(pessoa.getEmail())) {
				throw new EmailAlreadyUsedException();
			}
		}
		return dao.atualizar(pessoa);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) {
		Optional<Pessoa> pessoa = dao.encontrar(id);
		if(pessoa.isPresent()){
			dao.remover(id);
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + PessoaPerfil.class.getName());
		}
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<List<Pessoa>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Optional<Pessoa> encontrar(Long id) {
		Optional<Pessoa> pessoa = dao.encontrar(id);

		if(!pessoa.isPresent()){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + PessoaPerfil.class.getName());
		}
		return pessoa;
	}

}
