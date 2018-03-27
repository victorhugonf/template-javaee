package io.github.victorhugonf.javaee;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.victorhugonf.javaee.ejb.entity.Aircraft;
import io.github.victorhugonf.javaee.ejb.service.AircraftService;

@Path("aircraft")
public class AircraftEndPoint extends GenericEndPoint<Aircraft, AircraftService> {

	@Inject
	private AircraftService aircraftService;

	@Override
	protected AircraftService service() {
		return aircraftService;
	}

	@Override
	protected Class<Aircraft> getClazz() {
		return Aircraft.class;
	}



}