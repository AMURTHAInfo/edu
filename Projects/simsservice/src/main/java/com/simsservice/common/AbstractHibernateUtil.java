/**
 * @ (#) AbstractHibernateUtil.java
 * Project     : SIMS
 * File        : AbstractHibernateUtil.java
 * Author      : Ningannna C
 * Company     : 
 * Date Created: 15/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 * 
 * ========================================================================================================================
 */
package com.simsservice.common;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.orm.hibernate3.HibernateJdbcException;


/**
 * Hibernate Utility which deals with SessionFactory and Sessions.
 * Session-Per-Request Pattern.
 * 
 */
@SuppressWarnings("deprecation")
public abstract class AbstractHibernateUtil {
	private static final Logger LOGGER = Logger.getLogger(AbstractHibernateUtil.class);
	private static SessionFactory factory;
	private static Session session;
	private static final ThreadLocal<Session> CURRENTSESSION = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> TRANSACTIONHOLDER = new ThreadLocal<Transaction>();

	private AbstractHibernateUtil() {
		/* private constructor */
	}

	/**
	 * Create a session for each request Should be invoked in an
	 * Intercepter/Filter before executing the request if Session-per-request
	 * pattern is used
	 * 
	 * @throws SimsException
	 * @return session
	 */
	public static synchronized Session createSession(SessionFactory sessionFactory) throws SimsException {
		try {
			Session sessionl = (Session) CURRENTSESSION.get();
			if (sessionl == null) {
				sessionl = sessionFactory.openSession();
				session = sessionl;
				factory = sessionFactory;
				sessionl.setFlushMode(FlushMode.COMMIT);
				CURRENTSESSION.set(session);
			}
		} catch (HibernateJdbcException he) {
			LOGGER.error("Could not create Session", he);
			throw new SimsException(he.getMessage(), he.getSQLException().getErrorCode(), he);
		}
		return session;
	}

	/**
	 * This method will close the current session .Should be invoked in an
	 * Intercepter/filter after rendering the response if Session-per-request
	 * pattern is used
	 * 
	 * @throws SimsException
	 *             .
	 * @throws HibernateException
	 */
	public static void closeSession() {
		try {

			Session session = (Session) CURRENTSESSION.get();
			if (session != null) {
				session.close();
				CURRENTSESSION.set(null);
			}
		} catch (HibernateException he) {
			LOGGER.error("Session cannot be closed", he);
		}
	}

	/**
	 * Gives a sessionFactory.
	 * 
	 * @return factory
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}

	/**
	 * Gives the current session .Only this method should be used to get the
	 * current session in the application
	 * 
	 * @return session
	 */
	public static Session currentSession() {
		return CURRENTSESSION.get();
	}

	/**
	 * Builds Annotated Session factory . This should be invoked only at
	 * application startup Ex:- invoke from a ServletContextListener
	 * 
	 * @return factory.
	 */
	public static SessionFactory buildAnnotatedFactory() {
		LOGGER.info("AbstractHibernateUtil : SessionFactory ");
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		factory = cfg.configure().buildSessionFactory();
		LOGGER.info("Annotated SessionFactory built at server startup");
		return factory;
	}

	/**
	 * Start a transaction.
	 * 
	 * @throws SimsException
	 *             .
	 */
	public static void beginTransaction() throws SimsException {
		try {
			TRANSACTIONHOLDER.set(currentSession().beginTransaction());
		} catch (HibernateJdbcException he) {
			LOGGER.error("Could not begin Transaction", he);
			throw new SimsException(he.getMessage(), he.getSQLException().getErrorCode(), he);
		}
	}

	/**
	 * Commit the transaction.
	 * 
	 * @throws SimsException
	 *             .
	 */
	public static void commit() throws SimsException {
		Transaction tx = TRANSACTIONHOLDER.get();
		if (tx != null) {
			try {
				tx.commit();
			} catch (HibernateJdbcException he) {
				LOGGER.error("could not commit ", he);
				tx.rollback();
				throw new SimsException(he.getMessage(), he.getSQLException().getErrorCode(), he);
			}
		}
		TRANSACTIONHOLDER.set(null);
	}

	/**
	 * Rollback the transaction.
	 * 
	 * @throws SimsException
	 *             .
	 */
	public static void rollback() {
		Transaction tx = TRANSACTIONHOLDER.get();
		if (tx != null) {
			try {
				tx.rollback();
			} catch (HibernateJdbcException e) {
				LOGGER.error("Could not roll back", e);
			}
			TRANSACTIONHOLDER.set(null);
		}
	}

	/**
	 * Close the transaction.
	 * 
	 * @throws SimsException
	 *             .
	 */
	public static void endTransaction() {
		Transaction tx = TRANSACTIONHOLDER.get();
		if (tx != null) {
			try {
				TRANSACTIONHOLDER.set(null);
			} catch (HibernateJdbcException e) {
				LOGGER.error("Could not end Transaction", e);
			}
		}
	}
}
