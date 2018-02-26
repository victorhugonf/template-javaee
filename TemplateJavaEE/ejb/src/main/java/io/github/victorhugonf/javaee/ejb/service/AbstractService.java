package io.github.victorhugonf.javaee.ejb.service;

import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import io.github.victorhugonf.javaee.ejb.dao.AbstractDataAccessObject;
import io.github.victorhugonf.javaee.ejb.entity.ValueObject;
import io.github.victorhugonf.javaee.ejb.logerro.LogErrorInterceptor;

@Interceptors(LogErrorInterceptor.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class AbstractService <VO extends ValueObject,
									DAO extends AbstractDataAccessObject<VO>> 
									implements Service<VO>{
	
	protected abstract DAO dao();
	protected abstract Class<VO> getClassValueObject();
	
    public VO persist(VO valueObject) throws Exception {
    	validate(valueObject);
    	valueObject.validate();
    	validatePersist(valueObject);
		return dao().persist(valueObject);
    }

	private void validate(VO valueObject) throws Exception {
		if(valueObject == null){
    		throw new Exception(getClass().getName() + " not defined.");
    	}
	}
    
    protected abstract void validatePersist(VO valueObject) throws Exception;

    public VO merge(VO valueObject) throws Exception {
    	validate(valueObject);
    	valueObject.validate();
    	validateMerge(valueObject);
    	return dao().merge(valueObject);
    }
    
    protected abstract void validateMerge(VO valueObject) throws Exception;

    public void remove(long id) throws Exception {
    	remove(get(id));
    }
    
    public void remove(VO valueObject) throws Exception {
    	validate(valueObject);
    	validateRemove(valueObject);
    	dao().remove(valueObject);
    }
    
    protected abstract void validateRemove(VO valueObject) throws Exception;

    public List<VO> getAll() throws Exception {
        return dao().getAll();
    }
    
    public VO get(long id) throws Exception {
        return dao().get(id);
    }
    
    public VO get(VO valueObject) throws Exception {
        return dao().get(valueObject);
    }
    
}