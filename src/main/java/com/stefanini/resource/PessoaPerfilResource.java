package com.stefanini.resource;

import com.stefanini.model.PessoaPerfil;
import com.stefanini.servico.PessoaPerfilServico;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pessoa-perfils")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaPerfilResource {

	@Inject
	private PessoaPerfilServico pessoaPerfilServico;

	@GET
	public Response obterListaPessoaPerfil() {
		return Response.ok(pessoaPerfilServico.getList().get()).build();
	}

	@POST
	@Transactional
	public Response inserirPessoaPerfil(@Valid PessoaPerfil pessoaPerfil) {
		return Response.ok(pessoaPerfilServico.salvar(pessoaPerfil)).build();
	}

	@PUT
	@Transactional
	public Response alterarPessoaPerfil(@Valid PessoaPerfil pessoaPerfil) {return Response.ok(pessoaPerfilServico.atualizar(pessoaPerfil)).build();}

	@DELETE
	@Path("{id}")
	public void deletarPessoaPerfil(@PathParam("id") Long id) { pessoaPerfilServico.remover(id) ;}

	@GET
	@Path("{id}")
	public Response obterPessoaPerfil(@PathParam("id") Long id) {
		return Response.ok(pessoaPerfilServico.encontrar(id).get()).build();
	}

}
