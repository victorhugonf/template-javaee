package io.github.victorhugonf.javaee.ejb.service;

import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import io.github.victorhugonf.javaee.ejb.dao.AbstractDataAccessObject;
import io.github.victorhugonf.javaee.ejb.entity.Identifiable;
import io.github.victorhugonf.javaee.ejb.logerro.LogErrorInterceptor;

@Interceptors(LogErrorInterceptor.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class AbstractService <I extends Identifiable,
									DAO extends AbstractDataAccessObject<I>> 
									implements Service<I>{
	
	protected abstract DAO dao();
	protected abstract Class<I> getClazz();
	
    public I persist(I object) throws Exception {
    	validate(object);
    	object.validate();
    	validatePersist(object);
		return dao().persist(object);
    }

	private void validate(I object) throws Exception {
		if(object == null){
    		throw new Exception(getClazz().getName() + " not defined.");
    	}
	}
    
    protected abstract void validatePersist(I object) throws Exception;

    public I merge(I object) throws Exception {
    	validate(object);
    	object.validate();
    	validateMerge(object);
    	return dao().merge(object);
    }
    
    protected abstract void validateMerge(I object) throws Exception;

    public void remove(long id) throws Exception {
    	remove(get(id));
    }
    
    public void remove(I object) throws Exception {
    	validate(object);
    	validateRemove(object);
    	dao().remove(object);
    }
    
    protected abstract void validateRemove(I object) throws Exception;

    public List<I> getAll() throws Exception {
        return dao().getAll();
    }
    
    public I get(long id) throws Exception {
        return dao().get(id);
    }
    
    public I get(I object) throws Exception {
        return dao().get(object);
    }
    
}