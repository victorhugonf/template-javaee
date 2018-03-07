package io.github.victorhugonf.javaee.ejb.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import io.github.victorhugonf.javaee.ejb.dao.IndustryDao;
import io.github.victorhugonf.javaee.ejb.entity.Industry;

@Stateless
@LocalBean
public class IndustryService extends GenericService<Industry, IndustryDao> {
	   
	@EJB
	private IndustryDao industryDao;
	
	@Override
	protected IndustryDao dao() {
		return industryDao;
	}
	
	@Override
    protected void validatePersist(Industry object) throws Exception{
		
    }
    
    @Override
    protected void validateMerge(Industry object) throws Exception{
    	
    }
    
    @Override
	protected void validateRemove(Industry object) throws Exception {
				
	}
    
	public List<Industry> getByName(String name) throws Exception {
		if(StringUtils.isBlank(name)){
			throw new Exception("Please, informe the name.");
		}
		
		return dao().getByName(name);
    }

	@Override
	protected Class<Industry> getClazz() {
		return Industry.class;
	}
    
}