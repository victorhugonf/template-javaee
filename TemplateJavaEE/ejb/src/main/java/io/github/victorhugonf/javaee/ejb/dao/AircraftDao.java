package io.github.victorhugonf.javaee.ejb.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.victorhugonf.javaee.ejb.entity.Aircraft;
import io.github.victorhugonf.javaee.ejb.entity.Aircraft_;

@Stateless
@LocalBean
public class AircraftDao extends GenericDao<Aircraft> {

	@Override
	protected Class<Aircraft> getClazz() {
		return Aircraft.class;
	}

	public List<Aircraft> getByModel(String model) throws Exception {
		CriteriaQuery<Aircraft> cq = createQuery();
    	Root<Aircraft> root = createRoot(cq);

    	cq.where(getCriteriaBuilder()
    			.like(root.<String>get(Aircraft_.model), "%" + model + "%"));

//    	try{
    	return getEntityManager().createQuery(cq).getResultList();

//    	}catch(NoResultException e){
//    		return null;
//    	}
    }

}