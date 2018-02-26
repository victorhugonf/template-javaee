package io.github.victorhugonf.javaee.ejb.service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import io.github.victorhugonf.javaee.ejb.entity.Aircraft;
import io.github.victorhugonf.javaee.ejb.entity.Industry;

@Singleton
@LocalBean
@Startup
public class InitializeDbHyperSonicSqlService {
	   
	@EJB
	private IndustryService industryService;

	@EJB
	private AircraftService aircraftService;
	
	@PostConstruct
	private void initialize(){
		Industry boeing = new Industry();
		boeing.setName("Boeing");
		
		Industry embraer = new Industry();
		embraer.setName("Embraer");
		
		Industry airbus = new Industry();
		airbus.setName("Airbus");
		
		Aircraft b773 = new Aircraft();
		b773.setModel("777-300");
		b773.setIndustry(boeing);
		
		Aircraft b789 = new Aircraft();
		b789.setModel("787-9");
		b789.setIndustry(boeing);
		
		Aircraft a388 = new Aircraft();
		a388.setModel("A380-800");
		a388.setIndustry(airbus);
		
		Aircraft e195 = new Aircraft();
		e195.setModel("E-jet 195");
		e195.setIndustry(embraer);
	}
    
}