package com.stefanini.resource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.model.Pessoa;
import com.stefanini.servico.PessoaServico;

@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

	@Inject
	private PessoaServico pessoaServico;

	@GET
	public Response obterListaPessoa() {
		return Response.ok(pessoaServico.getList().get()).build();
	}

	@POST
	@Transactional
	public Response inserirPessoa(@Valid Pessoa pessoa) {
		return Response.ok(pessoaServico.salvar(pessoa)).build();
	}

	@PUT
	@Transactional
	public Response alterarPessoa(@Valid Pessoa pessoa) {return Response.ok(pessoaServico.atualizar(pessoa)).build();}

	@DELETE
	@Path("{id}")
	public void deletarPessoa(@PathParam("id") Long id) { pessoaServico.remover(id) ;}

	@GET
	@Path("{id}")
	public Response obterPessoa(@PathParam("id") Long id) {
		return Response.ok(pessoaServico.encontrar(id).get()).build();
	}

}
