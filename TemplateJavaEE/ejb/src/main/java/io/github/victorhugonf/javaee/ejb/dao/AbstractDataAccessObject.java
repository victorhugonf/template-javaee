package io.github.victorhugonf.javaee.ejb.dao;

import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.victorhugonf.javaee.ejb.entity.ValueObject;

@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class AbstractDataAccessObject <VO extends ValueObject> {

	@PersistenceContext
    private EntityManager entityManager;
	
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	
	protected CriteriaBuilder getCriteriaBuilder(){
		return getEntityManager().getCriteriaBuilder();
	}
	
	protected CriteriaQuery<VO> createQuery(){
		return getCriteriaBuilder().createQuery(getClassValueObject());
	}
	
	protected Root<VO> createRoot(CriteriaQuery<VO> criteriaQuery){
		return criteriaQuery.from(getClassValueObject());
	}
	
    public VO persist(VO valueObject) throws Exception {
    	getEntityManager().persist(valueObject);
    	return valueObject;
    }

    public VO merge(VO valueObject) throws Exception {
    	return getEntityManager().merge(valueObject);
    }

    public void remove(VO valueObject) throws Exception {
    	getEntityManager().remove(get(valueObject));
    }
    
	public VO get(VO valueObject) throws Exception {
		if(valueObject == null){
			return null;
		}
		
		return get(valueObject.getId());
	}
	
	protected abstract Class<VO> getClassValueObject();
	
	public VO get(long id) throws Exception {
		return getEntityManager().find(getClassValueObject(), id);
	}
    
	public List<VO> getAll() throws Exception {
    	CriteriaQuery<VO> cq = createQuery();
    	cq.select(createRoot(cq));
    	return (List<VO>) getEntityManager().createQuery(cq).getResultList();
    }
    
}