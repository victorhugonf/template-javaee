package io.github.victorhugonf.javaee.ejb.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import io.github.victorhugonf.javaee.ejb.dao.LogErrorDao;
import io.github.victorhugonf.javaee.ejb.entity.LogError;

@Stateless
@LocalBean
public class LogErrorService extends AbstractService<LogError, LogErrorDao>{
    
	@EJB
	private LogErrorDao logErrorDao;
	
	protected LogErrorDao dao() {
		return logErrorDao;
	}

	@Override
	protected void validatePersist(LogError valueObject) throws Exception {
		
	}

	@Override
	protected void validateMerge(LogError valueObject) throws Exception {
		throw new Exception("Log errors can not be merged.");
	}

	@Override
	protected void validateRemove(LogError valueObject) throws Exception {
		throw new Exception("Log errors can not be removed.");
	}

	@Override
	protected Class<LogError> getClassValueObject() {
		return LogError.class;
	}
	
}