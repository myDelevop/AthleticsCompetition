package it.uniba.athletics.util;

import it.uniba.athletics.util.properties.Configurations;
import it.uniba.athletics.util.properties.Properties;

public class Constants {

	// Constants (not user configurable)
	public static final String PATH_CONFIG_FILE = "/opt/athl/fe/config/configFE.properties";
	public static final String PATH_ERROR_FILE = "/opt/athl/fe/config/errorFE.properties";
	public static final String PATH_CONFIG_LOG4J = "/opt/athl/fe/config/log4j.properties";


	// Properties (configurable from file)
	public static final String EJB_PROVIDER_URL = Configurations.getInstance().getConfigProperty(Properties.EJB_PROVIDER_URL);
	public static final String EJB_INITIAL_CONTEXT_FACTORY = Configurations.getInstance().getConfigProperty(Properties.EJB_INITIAL_CONTEXT_FACTORY);
	public static final String EJB_LOOKUP_IAUTHUSER = Configurations.getInstance().getConfigProperty(Properties.EJB_LOOKUP_IAUTHUSER);

	// errors
	public static final String ERR_CONTEXT_INITIALIZATION = Configurations.getInstance().getConfigProperty(Properties.ERR_CONTEXT_INITIALIZATION);
	public static final String ERR_EJB_LOOKUP = Configurations.getInstance().getConfigProperty(Properties.ERR_EJB_LOOKUP);
	public static final String ERR_GENERIC = Configurations.getInstance().getConfigProperty(Properties.ERR_GENERIC);
	
		

}