package io.github.victorhugonf.javaee.rest;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.github.victorhugonf.javaee.dto.LogErrorDto;
import io.github.victorhugonf.javaee.ejb.entity.EntityIdentifiable;
import io.github.victorhugonf.javaee.ejb.service.Service;

@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public abstract class GenericEndPoint<E extends EntityIdentifiable, S extends Service<E>> {

	protected abstract S service();
	protected abstract Class<E> getClazz();

	protected Response responseOk() {
		return Response.ok().build();
	}

	protected Response responseOk(Object object) {
		return Response.ok(object).build();
	}

	protected Response responseNoContent() {
		return Response.noContent().build();
	}

	protected Response responseNotFound() {
		return Response.status(Status.NOT_FOUND).build();
	}

	protected Response handleError(Exception e) {
		e.printStackTrace();

		return Response
				.serverError()
				.entity(new LogErrorDto(e))
				.build();
	}

	@GET
	public Response getAll(){
		try {
			List<E> list = service().getAll();

			if(list == null || list.isEmpty()){
				return responseNoContent();
			}else{
				return responseOk(list);
			}
		} catch (Exception e) {
			return handleError(e);
		}
	}

	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id){
		try {
			E object = service().get(id);

			if(object == null){
				return responseNotFound();
			}else{
				return responseOk(object);
			}
		} catch (Exception e) {
			return handleError(e);
		}
	}

	@POST
	public Response post(@Valid E object){
		try {
			E persistedObject = service().persist(object);

			if(persistedObject == null){
				return responseNoContent();
			}else{
				return responseOk(persistedObject);
			}
		} catch (Exception e) {
			return handleError(e);
		}
	}

	@PUT
	@Path("{id}")
	public Response put(@PathParam("id") long id, @Valid  E object){
		try {
			if(object == null){
				throw new Exception(getClazz().getSimpleName() + " not defined.");
			}

			object.setId(id);

			E mergedObject = service().merge(object);

			if(mergedObject == null){
				return responseNoContent();
			}else{
				return responseOk(mergedObject);
			}
		} catch (Exception e) {
			return handleError(e);
		}
	}

	@PATCH
	@Path("{id}")
	public Response patch(@PathParam("id") long id, E object){
		try {
			if(object == null){
				throw new Exception(getClazz().getSimpleName() + " not defined.");
			}

			object.setId(id);

			//TODO: recuperar o objeto do banco e atribuir apenas os campos preenchidos

			E mergedObject = service().merge(object);

			if(mergedObject == null){
				return responseNoContent();
			}else{
				return responseOk(mergedObject);
			}
		} catch (Exception e) {
			return handleError(e);
		}
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id){
		try {
			service().remove(id);
			return responseOk();
		} catch (Exception e) {
			return handleError(e);
		}
	}

}