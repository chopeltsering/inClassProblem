package inClassProblem6part1;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectorFactory {
	
	public static ConnectionFactory getConnectionFactory(String provider) throws NamingException{
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL, provider);
		InitialContext jndiContext = null;
		
		/*
		 * Create a JNDI API InitialContext object
		 */
		try {
			jndiContext = new InitialContext(props);
		} catch (NamingException e) {
			System.err.println("Could not create JNDI API context: "
					+ e.toString());
			return null;
		}

		/*
		 * Look up connection factory.
		 */
		return (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
		
	}

	public static ConnectionFactory getConnectionFactory()
			throws NamingException {
		return getConnectionFactory(Broker.SERVICE);
	}

}
