package it.uniba.athletics.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@Singleton
@Startup
public class EjbContextListener {
    static final Logger LOGGER = Logger.getLogger(EjbContextListener.class);

	@PostConstruct
	public void init() {
		LOGGER.info("EJBContextListener Initialization of the Server...");
		
		LOGGER.debug("log4j initialization");
		try {
			PropertyConfigurator.configure(Constants.PATH_CONFIG_LOG4J);			
		} catch(Exception e) {
			LOGGER.error("Error in init: " + Constants.ERR_CONTEXT_INITIALIZATION, e);
		}
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("EJBContextListener Destroy of the Server...");
	}

}