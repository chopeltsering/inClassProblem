package inClassProblem6part2;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.network.NetworkConnector;

public class Broker {
	
	public static final String SERVICE = "tcp://localhost:61616"; //what is this?
	
	public static void main(String[] args) {
		try {
			startBroker("1", SERVICE, null);
			startBroker("2", "tcp://localhost:61617", "static://"+SERVICE);
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void startBroker() throws Exception {
		startBroker(null, SERVICE, null);
	}
	
	public static void startBroker(String name, String address, String networkConnector) throws Exception {
		BrokerService broker = new BrokerService();
		if (name != null)
			broker.setBrokerName(name);
		broker.setUseJmx(true);
		broker.addConnector(address);
		if (networkConnector != null) {
			NetworkConnector n = broker.addNetworkConnector(networkConnector);
			n.setDuplex(true);
		}
		broker.start();
	}
}
