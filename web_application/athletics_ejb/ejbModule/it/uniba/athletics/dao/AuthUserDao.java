package it.uniba.athletics.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import it.uniba.athletics.common.exception.BackEndException;
import it.uniba.athletics.entity.AuthUser;
import it.uniba.athletics.util.Constants;

@Local
@Stateless
public class AuthUserDao  {
	static final Logger LOGGER = Logger.getLogger(AuthUserDao.class);
	
	@PersistenceContext(unitName = Constants.PERSISTENCE_CONTEXT)
	private EntityManager em;
	
	public List<AuthUser> getAllUsers() throws BackEndException {
		LOGGER.info("START getAllUsers() - Getting all users from the database with Entity Manager.");

		List<AuthUser> res = null;

		try {					
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<AuthUser> cq = cb.createQuery(AuthUser.class);
	
			Root<AuthUser> rootEntry = cq.from(AuthUser.class);
			CriteriaQuery<AuthUser> all = cq.select(rootEntry);
			
			TypedQuery<AuthUser> allQuery = em.createQuery(all);
			
			res = allQuery.getResultList();

			LOGGER.debug("Founded " + res.size() + " Users in the system.\n" + res.toString());
			
		} catch(Exception e) {
			LOGGER.error("Error in getAllUsers: " + Constants.ERR_GET_USERS, e);
			throw new BackEndException(Constants.ERR_GET_USERS);			
		}
		
		LOGGER.info("END getAllUsers() - Returning all user from the database.");
		return res;
	}
	
	public AuthUser getById(Long id) throws BackEndException {
		LOGGER.info("START getById(id) - Get a specific user from the database with Entity Manager with id: ".concat(id.toString()).concat("."));

		AuthUser user = null;
		try {
			user = em.find(AuthUser.class, id);
			LOGGER.debug("Founded ".concat(user.toString()));
		} catch(Exception e) {
			LOGGER.error("Error in getById: " + Constants.ERR_GET_USERS, e);
			throw new BackEndException(Constants.ERR_GET_USERS);			
		}

		LOGGER.info("END getById(id) - Returning the user from the database with id: ".concat(id.toString()).concat("."));
		return user;
	}

	public AuthUser getByEmail(String email) throws BackEndException {
		LOGGER.info("START getByEmail(email) - Get a specific user from the database with Entity Manager with email: ".concat(email).concat("."));

		AuthUser user = new AuthUser();
		
		try {
			Query query = em.createQuery("from AuthUser where email = :email");
			query.setParameter("email", email);
			List<?> s = query.getResultList();
			
			if(s.size() == 1) {
				user = (AuthUser) s.get(0);
	        	user.setFounded(true);
				LOGGER.debug("Founded ".concat(user.toString()));
			} else if (s.size() != 0) {
				BackEndException e = new BackEndException(Constants.ERR_MORE_USER_SYSTEM);
				LOGGER.error("Error in getByEmail: " + Constants.ERR_MORE_USER_SYSTEM, e);
				throw e;
			}
		} catch(Exception e) {
			LOGGER.error("Error in getByEmail: " + Constants.ERR_GET_USERS, e);
			throw new BackEndException(Constants.ERR_GET_USERS);			
		}

		
		LOGGER.info("END getByEmail(email) - Returning the user from the database with email: ".concat(email).concat("."));		
		return user;
	}

	public AuthUser getByUsername(String username) throws BackEndException {
		LOGGER.info("START getByUsername(username) - Get a specific user from the database with Entity Manager with username: ".concat(username).concat("."));

		AuthUser user = new AuthUser();
		
		try {
			Query query = em.createQuery("from AuthUser where username = :username");
			query.setParameter("username", username);
			List<?> s = query.getResultList();
			
			if(s.size() == 1) {
				user = (AuthUser) s.get(0);		
	        	user.setFounded(true);
				LOGGER.debug("Founded ".concat(user.toString()));
			} else if (s.size() != 0) {
				BackEndException e = new BackEndException(Constants.ERR_MORE_USER_SYSTEM);
				LOGGER.error("Error in getByUsername: " + Constants.ERR_MORE_USER_SYSTEM, e);
				throw e;
			}
		} catch(Exception e) {
			LOGGER.error("Error in getByUsername: " + Constants.ERR_GET_USERS, e);
			throw new BackEndException(Constants.ERR_GET_USERS);			
		}
					
		LOGGER.info("END getByUsername (username) - Returning the user from the database with email: ".concat(username).concat("."));				
		return user;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public AuthUser saveUser(AuthUser user) throws BackEndException {
		LOGGER.info("START saveUser(user) - Persistence of user with EntityManager");
		
		try {
			em.persist(user);
			em.flush();			
		} catch(Exception e) {
			LOGGER.error("Error in saveUser: " + Constants.ERR_PERSISTENCE, e);
			throw new BackEndException(Constants.ERR_PERSISTENCE);			
		}

		
		LOGGER.info("END saveUser(user) - User persisted with EntityManager");
		return user;
	}

	public AuthUser login(String username, String password) throws BackEndException {
		LOGGER.info("START login(user, password) - Check the correctness of the login parameters");

		AuthUser user = new AuthUser(username);

		try {	
	        Pattern pattern = Pattern.compile(Constants.REGEX_EMAIL);
	        Matcher mat = pattern.matcher(username);
	        
	        if(mat.matches()) {
	        	user = this.getByEmail(username);
	    		LOGGER.debug("The username an email. Founded the user in the system with the following email: " + username);
	        } else {
	        	user = this.getByUsername(username);
	       		LOGGER.debug("The username is not an email. Founded the user in the system with the following username: " + username);
	        }
	
			if(user.isFounded() && user.getAuthPsw().getPsw().equals(password)) {
				LOGGER.debug("user " + user.getUsername() + "logged successfully =)\nWelcome to the system");
				user.setLogged(true);
			} else {
				LOGGER.debug("user " + username + "not logged. Check the parameters =(");
			}
		} catch(Exception e) {
			LOGGER.error("Error in login: " + Constants.ERR_DATABASE, e);
			throw new BackEndException(Constants.ERR_DATABASE);			
		}

		LOGGER.info("END login(user, password) - parameters checked correctly");
		return user;
	}

}
