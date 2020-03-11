package com.stefanini.resource;

import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.EnderecoServico;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

	@Inject
	private EnderecoServico enderecoServico;

	@GET
	public Response obterListaEndereco() {
		return Response.ok(enderecoServico.getList().get()).build();
	}

	@POST
	@Transactional
	public Response inserirEndereco(@Valid Endereco endereco) {
		System.out.println(endereco);
		return Response.ok(enderecoServico.salvar(endereco)).build();
	}

	@PUT
	@Transactional
	public Response alterarEndereco(@Valid Endereco endereco) {return Response.ok(enderecoServico.atualizar(endereco)).build();}

	@DELETE
	@Transactional
	@Path("{id}")
	public void deletarEndereco(@PathParam("id") Long id) { enderecoServico.remover(id) ;}

	@GET
	@Path("{id}")
	public Response obterEndereco(@PathParam("id") Long id) {
		return Response.ok(enderecoServico.encontrar(id).get()).build();
	}

}
