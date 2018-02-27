package io.github.victorhugonf.javaee.ejb.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.victorhugonf.javaee.ejb.entity.Industry;
import io.github.victorhugonf.javaee.ejb.utils.CONSTANTS;

@Stateless
@LocalBean
public class IndustryDao extends AbstractDataAccessObject<Industry> {

//	@PersistenceContext
//    private EntityManager _entityManager;
//	
//	@Override
//	protected EntityManager entityManager() {
//		return _entityManager;
//	}

	@Override
	protected Class<Industry> getClazz() {
		return Industry.class;
	}
	
	public List<Industry> getByName(String name) throws Exception {
		CriteriaQuery<Industry> cq = createQuery();
    	Root<Industry> root = createRoot(cq);
    	
    	//TODO: mudar para metamodel
    	cq.where(getCriteriaBuilder()
    			.like(root.<String>get(CONSTANTS.DATA_BASE.TABLES.INDUSTRIES.COLUMNS.NAME),
    					"%" + name + "%"));
    	
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