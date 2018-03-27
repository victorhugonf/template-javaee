package io.github.victorhugonf.javaee;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.victorhugonf.javaee.ejb.entity.LogError;
import io.github.victorhugonf.javaee.ejb.service.LogErrorService;

@Path("logerror")
public class LogErrorEndPoint extends GenericEndPoint<LogError, LogErrorService> {

	@Inject
	private LogErrorService logErrorService;

	@Override
	protected LogErrorService service() {
		return logErrorService;
	}

	@Override
	protected Class<LogError> getClazz() {
		return LogError.class;
	}



}