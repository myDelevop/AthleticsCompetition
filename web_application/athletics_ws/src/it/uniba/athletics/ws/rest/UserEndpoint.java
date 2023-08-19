package it.uniba.athletics.ws.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
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

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint implements Serializable {
	
	static final Logger LOGGER = Logger.getLogger(UserEndpoint.class);

	private static final long serialVersionUID = 1L;
    private IAuthUser s;
 
	@GET
	@Path("/getAllUsers")
    public Response getAllUsers() {
		LOGGER.info("Invocation of http method GET getAllUsers");
		List<AuthUserDTO> res = new ArrayList<AuthUserDTO>();

		try {
			LOGGER.info("Waiting for IAuthUser EJB lookup... ");
			s = (IAuthUser) LookupUtil.lookupEjb(Constants.EJB_LOOKUP_IAUTHUSER);
			LOGGER.info("DONE! - IAuthUser EJB lookup!");

			res = s.getAllUsers();
			
			LOGGER.debug(res);
		} catch (FrontEndException e) {
			String strError = "http-GET getAllUsers -> Error in the front-end system " + e.getMessage();
			LOGGER.error(strError);
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(strError).build();
		} catch (BackEndException e) {
			String strError = "http-GET getAllUsers -> Error in the back-end system " + e.getMessage();
			LOGGER.error(strError);
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(strError).build();
		} catch (Exception e) {
			String strError = "http-GET getAllUsers -> " + e.getMessage();
			LOGGER.error(strError);
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(strError).build();
		}


		LOGGER.info("End of http method GET getAllUsers");
		return Response.ok(res, MediaType.APPLICATION_JSON).build();

    }

}
