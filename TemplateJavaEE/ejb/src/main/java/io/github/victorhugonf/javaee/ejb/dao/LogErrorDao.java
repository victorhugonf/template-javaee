package io.github.victorhugonf.javaee.ejb.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import io.github.victorhugonf.javaee.ejb.entity.LogError;

@Stateless
@LocalBean
public class LogErrorDao extends AbstractDataAccessObject<LogError> {

	@Override
	protected Class<LogError> getClazz() {
		return LogError.class;
	}
    
}