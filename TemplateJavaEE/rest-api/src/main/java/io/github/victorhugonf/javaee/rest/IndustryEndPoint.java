package io.github.victorhugonf.javaee.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.victorhugonf.javaee.ejb.entity.Industry;
import io.github.victorhugonf.javaee.ejb.service.IndustryService;

@Path("industries")
public class IndustryEndPoint extends GenericEndPoint<Industry, IndustryService> {

	@Inject
	private IndustryService industryService;

	@Override
	protected IndustryService service() {
		return industryService;
	}

	@Override
	protected Class<Industry> getClazz() {
		return Industry.class;
	}



}