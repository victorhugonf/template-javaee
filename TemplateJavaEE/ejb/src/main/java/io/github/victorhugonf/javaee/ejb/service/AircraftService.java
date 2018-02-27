package io.github.victorhugonf.javaee.ejb.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import io.github.victorhugonf.javaee.ejb.dao.AircraftDao;
import io.github.victorhugonf.javaee.ejb.entity.Aircraft;

@Stateless
@LocalBean
public class AircraftService extends AbstractService<Aircraft, AircraftDao> {
	   
	@EJB
	private AircraftDao aircraftDao;
	
	@Override
	protected AircraftDao dao() {
		return aircraftDao;
	}
	
	@Override
    protected void validatePersist(Aircraft object) throws Exception{
		
    }
    
    @Override
    protected void validateMerge(Aircraft object) throws Exception{
    	
    }
    
    @Override
	protected void validateRemove(Aircraft object) throws Exception {
				
	}
    
	public List<Aircraft> getByModel(String model) throws Exception {
		if(StringUtils.isBlank(model)){
			throw new Exception("Please, informe the model.");
		}
		
		return dao().getByModel(model);
    }

	@Override
	protected Class<Aircraft> getClazz() {
		return Aircraft.class;
	}
    
}