package io.github.victorhugonf.javaee.ejb.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.victorhugonf.javaee.ejb.entity.Industry;
import io.github.victorhugonf.javaee.ejb.entity.Industry_;

@Stateless
@LocalBean
public class IndustryDao extends GenericDao<Industry> {

	@Override
	protected Class<Industry> getClazz() {
		return Industry.class;
	}

	public List<Industry> getByName(String name) throws Exception {
		CriteriaQuery<Industry> cq = createQuery();
    	Root<Industry> root = createRoot(cq);

    	cq.where(getCriteriaBuilder()
    			.like(root.<String>get(Industry_.name), "%" + name + "%"));

//    	try{
    	return getEntityManager().createQuery(cq).getResultList();

//    	}catch(NoResultException e){
//    		return null;
//    	}
    }

}