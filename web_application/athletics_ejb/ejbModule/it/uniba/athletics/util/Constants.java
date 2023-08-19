package it.uniba.athletics.util;

import it.uniba.athletics.util.properties.Configurations;
import it.uniba.athletics.util.properties.Properties;

public class Constants {

	// Constants (not user configurable)
	public static final String PATH_CONFIG_FILE = "/opt/athl/be/config/configBE.properties";
	public static final String PATH_ERROR_FILE = "/opt/athl/be/config/errorBE.properties";
	public static final String PATH_CONFIG_LOG4J = "/opt/athl/be/config/log4j.properties";
	public static final String PERSISTENCE_CONTEXT = "athletics_ejb";


	// Properties (configurable from file)	
	public static final String REGEX_EMAIL = Configurations.getInstance().getConfigProperty(Properties.REGEX_EMAIL);

	// errors
	public static final String ERR_CONTEXT_INITIALIZATION= Configurations.getInstance().getErrorMessage(Properties.ERR_CONTEXT_INITIALIZATION);

	public static final String ERR_GET_USERS = Configurations.getInstance().getErrorMessage(Properties.ERR_GET_USERS);
	public static final String ERR_MORE_USER_SYSTEM = Configurations.getInstance().getErrorMessage(Properties.ERR_MORE_USER_SYSTEM);
	public static final String ERR_PERSISTENCE = Configurations.getInstance().getErrorMessage(Properties.ERR_PERSISTENCE);
	public static final String ERR_DATABASE = Configurations.getInstance().getErrorMessage(Properties.ERR_DATABASE);
	public static final String ERR_CONVERSION = Configurations.getInstance().getErrorMessage(Properties.ERR_CONVERSION);
	public static final String ERR_GENERIC = Configurations.getInstance().getErrorMessage(Properties.ERR_GENERIC);
	
	
		
	

}