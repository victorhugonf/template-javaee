package io.github.victorhugonf.javaee;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.victorhugonf.javaee.ejb.entity.Industry;
import io.github.victorhugonf.javaee.ejb.service.IndustryService;

@Path("industry")
public class EndPointIndustry extends AbstractEndPoint<Industry, IndustryService> {
	   
	@Inject
	private IndustryService industryService;
	
	@Override
	protected IndustryService service() {
		return industryService;
	}

	@Override
	protected Class<Industry> getClassValueObject() {
		return Industry.class;
	}
	
	
    
}