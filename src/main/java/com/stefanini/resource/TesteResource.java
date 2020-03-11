package com.stefanini.resource;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.model.Pessoa;
import com.stefanini.servico.PessoaServico;

@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TesteResource {

	@Inject
	private PessoaServico pessoaServico;

	@GET
	public Response obterListaPessoa() {
		return Response.ok(pessoaServico.getList().get()).build();
	}

	@POST
	public Response inserirPessoa(@Valid Pessoa pessoa) {
		return Response.ok(pessoaServico.salvar(pessoa)).build();
	}

	@PUT
	public Response alterarPessoa(@Valid Pessoa pessoa) {return Response.ok(pessoaServico.atualizar(pessoa)).build();}

//	@DELETE
//	public Response deletarPessoa(@PathParam("id") Long id) { pessoaServico.remover(id) ;}

	@GET
	@Path("{id}")
	public Response obterPessoa(@PathParam("id") Long id) {
//		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("deu ruim").build();
		return Response.ok(pessoaServico.encontrar(id).get()).build();
	}

}
