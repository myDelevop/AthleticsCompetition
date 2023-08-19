package it.uniba.athletics.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import it.uniba.athletics.common.exception.FrontEndException;
import it.uniba.athletics.util.Constants;

public class LookupUtil {
	static final Logger LOGGER = Logger.getLogger(LookupUtil.class);

	public static Object lookupEjb(String ejbName) throws FrontEndException {
		Object obj = null;
		try {
			final Properties props = new Properties();
			
			props.put(Context.INITIAL_CONTEXT_FACTORY,  Constants.EJB_INITIAL_CONTEXT_FACTORY);
			props.put(Context.PROVIDER_URL, Constants.EJB_PROVIDER_URL);

			Context ctx = new InitialContext(props);
			obj = ctx.lookup(ejbName);
		} catch (NamingException e) {
			LOGGER.error("Error in getAllUsers: " + Constants.ERR_EJB_LOOKUP, e);
			throw new FrontEndException(Constants.ERR_EJB_LOOKUP);			
		}
		
		return obj;
	}
}
