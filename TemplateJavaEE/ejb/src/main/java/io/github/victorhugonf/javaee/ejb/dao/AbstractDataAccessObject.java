package io.github.victorhugonf.javaee.ejb.dao;

import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.victorhugonf.javaee.ejb.entity.Identifiable;

@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class AbstractDataAccessObject <I extends Identifiable> {

	@PersistenceContext
    private EntityManager entityManager;
	
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	
	protected CriteriaBuilder getCriteriaBuilder(){
		return getEntityManager().getCriteriaBuilder();
	}
	
	protected CriteriaQuery<I> createQuery(){
		return getCriteriaBuilder().createQuery(getClazz());
	}
	
	protected Root<I> createRoot(CriteriaQuery<I> criteriaQuery){
		return criteriaQuery.from(getClazz());
	}
	
    public I persist(I object) throws Exception {
    	getEntityManager().persist(object);
    	return object;
    }

    public I merge(I object) throws Exception {
    	return getEntityManager().merge(object);
    }

    public void remove(I object) throws Exception {
    	getEntityManager().remove(get(object));
    }
    
	public I get(I object) throws Exception {
		if(object == null){
			return null;
		}
		
		return get(object.getId());
	}
	
	protected abstract Class<I> getClazz();
	
	public I get(long id) throws Exception {
		return getEntityManager().find(getClazz(), id);
	}
    
	public List<I> getAll() throws Exception {
    	CriteriaQuery<I> cq = createQuery();
    	cq.select(createRoot(cq));
    	return (List<I>) getEntityManager().createQuery(cq).getResultList();
    }
    
}