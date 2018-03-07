package io.github.victorhugonf.javaee.ejb.dao;

import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.victorhugonf.javaee.ejb.entity.EntityIdentifiable;

@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class GenericDao <E extends EntityIdentifiable> {

	@PersistenceContext
    private EntityManager entityManager;
	
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	
	protected CriteriaBuilder getCriteriaBuilder(){
		return getEntityManager().getCriteriaBuilder();
	}
	
	protected CriteriaQuery<E> createQuery(){
		return getCriteriaBuilder().createQuery(getClazz());
	}
	
	protected Root<E> createRoot(CriteriaQuery<E> criteriaQuery){
		return criteriaQuery.from(getClazz());
	}
	
    public E persist(E object) throws Exception {
    	getEntityManager().persist(object);
    	return object;
    }

    public E merge(E object) throws Exception {
    	return getEntityManager().merge(object);
    }

    public void remove(E object) throws Exception {
    	getEntityManager().remove(get(object));
    }
    
	public E get(E object) throws Exception {
		if(object == null){
			return null;
		}
		
		return get(object.getId());
	}
	
	protected abstract Class<E> getClazz();
	
	public E get(long id) throws Exception {
		return getEntityManager().find(getClazz(), id);
	}
    
	public List<E> getAll() throws Exception {
    	CriteriaQuery<E> cq = createQuery();
    	cq.select(createRoot(cq));
    	return (List<E>) getEntityManager().createQuery(cq).getResultList();
    }
    
}