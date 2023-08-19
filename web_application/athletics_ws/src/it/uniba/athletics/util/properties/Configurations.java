package it.uniba.athletics.util.properties;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import it.uniba.athletics.util.Constants;
import it.uniba.athletics.util.properties.Configurations;

public final class Configurations {

	static final Logger LOGGER = Logger.getLogger(Configurations.class);

	private Properties configProperties = null;
	private Properties errorProperties = null;

	private static Configurations instance = null;

	/** Private constructor */
	private Configurations () {
		LOGGER.info("Configurations - Private constructor");

		this.configProperties = new Properties();
		this.errorProperties = new Properties();
		
		try {
			configProperties.load(new FileInputStream(Constants.PATH_CONFIG_FILE));
			LOGGER.debug("configProperty:" + configProperties);

			errorProperties.load(new FileInputStream(Constants.PATH_ERROR_FILE));
			LOGGER.debug("errorProperty:" + errorProperties);
		}catch(Exception e) {
			LOGGER.error("Error in Configurations Singleton - Private constructor", e);
		}
	}   

	/** Creates the instance is synchronized to avoid multithreads problems */
	private synchronized static void createInstance () {
		LOGGER.info("Creation of the instance synchronized in order to avoid multhithread problems");
		if (instance == null) { 
			instance = new Configurations();
		}
	}

	/** Get the properties instance. Uses singleton pattern */
	public static Configurations getInstance(){
		// Uses singleton pattern to guarantee the creation of only one instance
		if(instance == null) {
			LOGGER.info("Initialization of the Configurations Singleton");
			createInstance();
		}
		return instance;
	}

	/** Get a property of the property file */
	public String getConfigProperty(String key) {
		LOGGER.debug("Getting the following property: " + key);

		String result = null;
		if(key !=null && !key.trim().isEmpty()) {
			result = this.configProperties.getProperty(key);
		}
		return result;
	}

	/** Get a property of the property file */
	public String getErrorMessage(String errorCode) {
		LOGGER.debug("Getting the error property: " + errorCode);

		String result = null;
		if(errorCode!=null && !errorCode.trim().isEmpty()) {
			result = this.errorProperties.getProperty(String.valueOf(errorCode));
		}
		return result;
	}

	/** Override the clone method to ensure the "unique instance" requeriment of this class */
	public Object clone() throws CloneNotSupportedException {
		LOGGER.error("Impossible to clone this object");
		throw new CloneNotSupportedException();
	}
}