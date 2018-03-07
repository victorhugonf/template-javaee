package io.github.victorhugonf.javaee.ejb.service;

import java.util.List;

import io.github.victorhugonf.javaee.ejb.entity.EntityIdentifiable;

public interface Service <I extends EntityIdentifiable> {
	
    I persist(I object) throws Exception;

	I merge(I object) throws Exception;
    
	void remove(long id) throws Exception;
    
    void remove(I object) throws Exception;
    
    List<I> getAll() throws Exception;
    
    I get(long id) throws Exception;
    
    I get(I object) throws Exception;
    
}