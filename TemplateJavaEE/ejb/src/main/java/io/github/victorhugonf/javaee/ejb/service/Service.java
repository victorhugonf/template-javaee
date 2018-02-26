package io.github.victorhugonf.javaee.ejb.service;

import java.util.List;

import io.github.victorhugonf.javaee.ejb.entity.ValueObject;

public interface Service <VO extends ValueObject> {
	
    VO persist(VO valueObject) throws Exception;

	VO merge(VO valueObject) throws Exception;
    
	void remove(long id) throws Exception;
    
    void remove(VO valueObject) throws Exception;
    
    List<VO> getAll() throws Exception;
    
    VO get(long id) throws Exception;
    
    VO get(VO valueObject) throws Exception;
    
}