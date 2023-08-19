package it.uniba.athletics.ws.rest;

import java.io.Serializable;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import it.uniba.athletics.common.dto.AuthUserDTO;
import it.uniba.athletics.common.exception.BackEndException;
import it.uniba.athletics.common.exception.FrontEndException;
import it.uniba.athletics.common.interfaces.IAuthUser;
import it.uniba.athletics.util.Constants;
import it.uniba.athletics.util.LookupUtil;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthEndpoint implements Serializable {
    static final Logger LOGGER = Logger.getLogger(AuthEndpoint.class);


	private static final long serialVersionUID = 1L;
    private IAuthUser s;
 
	@POST
	@Path("/login")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
		LOGGER.info("Invocation of http method GET login");
		
		AuthUserDTO userDTO = null;

		
		LOGGER.debug("Authentication with following parameters: username: " + username + " - password: " + password);
		Response res = Response.status(Response.Status.UNAUTHORIZED).build();
		
		try {
			LOGGER.info("Waiting for IAuthUser EJB lookup... ");
			s = (IAuthUser) LookupUtil.lookupEjb(Constants.EJB_LOOKUP_IAUTHUSER);
			LOGGER.info("DONE! - IAuthUser EJB looked-up!!");
			userDTO = s.login(username, password);
			
			if(userDTO.isFounded() && userDTO.isLogged()) {
				LOGGER.debug("User logged in =)" );
				res = Response.ok(userDTO, MediaType.APPLICATION_JSON).build();			
			} else if(userDTO.isFounded() && !userDTO.isLogged()) {
		        res = Response.status(Response.Status.UNAUTHORIZED).build();
			} else if(!userDTO.isFounded()) {
				LOGGER.debug("User not esistent" );
				res = Response.ok("NOEXISTS", MediaType.APPLICATION_JSON).build();	
			}

		} catch (FrontEndException e) {
			String strError = "http-GET login -> Error in the front-end system " + e.getMessage();
			LOGGER.error(strError);
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(strError).build();
		} catch (BackEndException e) {
			String strError = "http-GET login -> Error in the back-end system " + e.getMessage();
			LOGGER.error(strError);
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(strError).build();
		} catch (Exception e) {
			String strError = "http-GET login -> " + e.getMessage();
			LOGGER.error(strError);
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(strError).build();
		}
		
		
		LOGGER.info("End of http method GET login");
		return res;
    }

}
