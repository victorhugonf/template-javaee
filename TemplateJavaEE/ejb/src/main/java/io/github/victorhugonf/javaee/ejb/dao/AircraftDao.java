package io.github.victorhugonf.javaee.ejb.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.victorhugonf.javaee.ejb.entity.Aircraft;
import io.github.victorhugonf.javaee.ejb.utils.CONSTANTS;

@Stateless
@LocalBean
public class AircraftDao extends GenericDao<Aircraft> {

//	@PersistenceContext
//    private EntityManager _entityManager;
//	
//	@Override
//	protected EntityManager entityManager() {
//		return _entityManager;
//	}

	@Override
	protected Class<Aircraft> getClazz() {
		return Aircraft.class;
	}
	
	public List<Aircraft> getByModel(String model) throws Exception {
		CriteriaQuery<Aircraft> cq = createQuery();
    	Root<Aircraft> root = createRoot(cq);
    	
    	//TODO: mudar para metamodel
    	cq.where(getCriteriaBuilder()
    			.like(root.<String>get(CONSTANTS.DATA_BASE.TABLES.AIRCRAFTS.COLUMNS.MODEL),
    					"%" + model + "%"));
    	
//    	try{
    	return getEntityManager().createQuery(cq).getResultList();
    		
//    	}catch(NoResultException e){
//    		return null;
//    	}

    		
//    	CriteriaQuery<PersonVO> criteriaQuery = criteriaBuilder().createQuery(PersonVO.class);
//    	
//    	Root<PersonVO> root = criteriaQuery.from(PersonVO.class);
//    	
//    	criteriaQuery.where(criteriaBuilder()
//    			.like(root.get(Strings.DataBase.Tables.People.Columns.NAME),
//    					criteriaBuilder().parameter(String.class, Strings.DataBase.Tables.People.Columns.NAME)));
//    	
//    	TypedQuery<PersonVO> typedQuery = entityManager().createQuery(criteriaQuery);
//    	typedQuery.setParameter(Strings.DataBase.Tables.People.Columns.NAME, name);
//    	
//    	try{
//    		return (PersonVO) typedQuery.getSingleResult();
//    	}catch(NoResultException e){
//    		return null;
//    	}
    }
    
}