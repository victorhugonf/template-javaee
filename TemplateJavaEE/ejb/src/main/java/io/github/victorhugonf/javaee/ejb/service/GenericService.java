package io.github.victorhugonf.javaee.ejb.service;

import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import io.github.victorhugonf.javaee.ejb.dao.GenericDao;
import io.github.victorhugonf.javaee.ejb.entity.EntityIdentifiable;
import io.github.victorhugonf.javaee.ejb.logerro.LogErrorInterceptor;

@Interceptors(LogErrorInterceptor.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class GenericService <E extends EntityIdentifiable,
									D extends GenericDao<E>>
									implements Service<E>{

	protected abstract D dao();
	protected abstract Class<E> getClazz();

	@Override
    public E persist(E object) throws Exception {
    	validate(object);
    	validatePersist(object);
		return dao().persist(object);
    }

	private void validate(E object) throws Exception {
		if(object == null){
    		throw new Exception(getClazz().getSimpleName() + " not defined.");
    	}
	}

    protected abstract void validatePersist(E object) throws Exception;

    @Override
    public E merge(E object) throws Exception {
    	validate(object);
    	validateMerge(object);
    	return dao().merge(object);
    }

    protected abstract void validateMerge(E object) throws Exception;

    @Override
    public void remove(long id) throws Exception {
    	remove(get(id));
    }

    @Override
    public void remove(E object) throws Exception {
    	validate(object);
    	validateRemove(object);
    	dao().remove(object);
    }

    protected abstract void validateRemove(E object) throws Exception;

    @Override
    public List<E> getAll() throws Exception {
        return dao().getAll();
    }

    @Override
    public E get(long id) throws Exception {
        return dao().get(id);
    }

    @Override
    public E get(E object) throws Exception {
        return dao().get(object);
    }

}