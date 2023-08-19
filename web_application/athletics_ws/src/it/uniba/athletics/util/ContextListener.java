package it.uniba.athletics.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import it.uniba.athletics.util.Constants;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

	static final Logger LOGGER = Logger.getLogger(ContextListener.class);
 
    @Override
    public void contextInitialized(ServletContextEvent event) {
		LOGGER.info("ContextListener Initialization of the Server...");
		
		LOGGER.debug("log4j initialization");
		try {
			PropertyConfigurator.configure(Constants.PATH_CONFIG_LOG4J);			
		} catch(Exception e) {
			LOGGER.error("Error in init: " + Constants.ERR_CONTEXT_INITIALIZATION, e);
		}
    }
     
    @Override
    public void contextDestroyed(ServletContextEvent event) {
		LOGGER.info("ContextListener Destroy of the Server...");
    }  
}