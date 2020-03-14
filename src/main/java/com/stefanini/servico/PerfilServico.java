package com.stefanini.servico;

import com.stefanini.dao.PerfilDao;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;
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
		Optional<Perfil> p = dao.encontrar(entity.getId());

		if(!p.isPresent()){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + entity.getId()
					+ ", Tipo: " + Perfil.class.getName());
		}

		return dao.atualizar(entity);
	}

	/**
	 * Remover os dados de um Perfil
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
		Optional<Perfil> perfil = dao.encontrar(id);
		if(perfil.isPresent()){
			dao.remover(id);
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Perfil.class.getName());
		}
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
		Optional<Perfil> perfil = dao.encontrar(id);
		if(!perfil.isPresent()){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Perfil.class.getName());
		}

		return perfil;
	}
}
