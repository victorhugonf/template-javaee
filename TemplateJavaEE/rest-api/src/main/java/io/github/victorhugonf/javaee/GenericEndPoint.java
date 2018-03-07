package io.github.victorhugonf.javaee;

import java.util.List;

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

import io.github.victorhugonf.javaee.ejb.entity.LogError;
import io.github.victorhugonf.javaee.ejb.entity.EntityIdentifiable;
import io.github.victorhugonf.javaee.ejb.service.Service;

@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public abstract class GenericEndPoint<E extends EntityIdentifiable, S extends Service<E>> {
	
	abstract protected S service();
	abstract protected Class<E> getClazz();
	
//	protected abstract String getPath();
//	protected abstract long getId(PO po);
//	protected abstract DTO parseDataTransferObject(PO po);
//	protected abstract PO parsePersistentObject(DTO dto);
	
	

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
				.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new LogError(e))
				.build();
	}
	
	@GET
	public Response getAll(){
		try {
			List<E> valueObjects = service().getAll();

			if(valueObjects == null || valueObjects.isEmpty()){
				return responseNoContent();
			}else{
				return responseOk(valueObjects);
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
				return responseNoContent();
			}else{
				return responseOk(object);
			}
		} catch (Exception e) {
			return handleError(e);
		}
	}

	@POST
	public Response post(E object){
		try {
			E objectPersisted = service().persist(object);
			
			if(objectPersisted == null){
				return responseNoContent();
			}else{
				return responseOk(objectPersisted);
			}
		} catch (Exception e) {
			return handleError(e);
		}
	}
	
	@PUT
	@Path("{id}")
	public Response put(@PathParam("id") long id, E object){
		try {
			if(object == null){
				throw new Exception(getClazz().getName() + " not defined.");
			}
			
			object.setId(id);
			
			E objectMerged = service().merge(object);
			
			if(objectMerged == null){
				return responseNoContent();
			}else{
				return responseOk(objectMerged);
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
				throw new Exception(getClazz().getName() + " not defined.");
			}
			
			object.setId(id);
			
			//TODO: recuperar o objeto do banco e atribuir apenas os campos preenchidos
			
			E objectMerged = service().merge(object);
			
			if(objectMerged == null){
				return responseNoContent();
			}else{
				return responseOk(objectMerged);
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