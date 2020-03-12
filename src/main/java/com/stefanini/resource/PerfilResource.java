package com.stefanini.resource;

import com.stefanini.model.Perfil;
import com.stefanini.servico.PerfilServico;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("perfils")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilResource {

	@Inject
	private PerfilServico perfilServico;

	@GET
	public Response obterListaPerfil() {
		return Response.ok(perfilServico.getList().get()).build();
	}

	@POST
	@Transactional
	public Response inserirPerfil(@Valid Perfil perfil) {
		return Response.ok(perfilServico.salvar(perfil)).build();
	}

	@PUT
	@Transactional
	public Response alterarPerfil(@Valid Perfil perfil) {return Response.ok(perfilServico.atualizar(perfil)).build();}

	@DELETE
	@Transactional
	@Path("{id}")
	public void deletarPerfil(@PathParam("id") Long id) { perfilServico.remover(id) ;}

	@GET
	@Path("{id}")
	public Response obterPerfil(@PathParam("id") Long id) {
		return Response.ok(perfilServico.encontrar(id).get()).build();
	}

}
