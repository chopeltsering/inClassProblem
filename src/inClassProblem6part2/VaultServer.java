package inClassProblem6part2;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class VaultServer implements Runnable {

	private boolean bRunning = false;
	private int msgCount = 0;
	public static String TOPIC = "Services";

	public VaultServer() {
	}

	public static void main(String[] args) {
		VaultServer p = new VaultServer();
		Thread t = new Thread(p);
		t.start();
		try {
			t.join();                          //join?
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		bRunning = true;
		while (bRunning) {
			javax.jms.Connection connection = null;
			try {
				++msgCount;
				System.out.println("Published so far : "+ msgCount);

				ConnectionFactory factory = ConnectorFactory.getConnectionFactory(Broker.SERVICE);
				connection = factory.createConnection();
				connection.start();

				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
				javax.jms.Topic topic = session.createTopic(TOPIC);
				System.out.println("Published topid: " + topic);

				MessageProducer producer = session.createProducer((Destination) topic);
				producer.setTimeToLive(10000);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				TextMessage message = session.createTextMessage("current time in hours : " + System.currentTimeMillis()/1000*60*60);
				producer.send(message);

				connection.close(); 
				connection = null;

				if (bRunning) {
					Thread.sleep((int) (Math.random() * 2 * 500));
				}
			}

			catch (Exception e) {
				System.out.println("Exception occurred: " + e.toString());
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (JMSException e) {
					}
				}
			}
		} // End while

		System.out.println("Publisher.run(). Stopped.");
	}

	public void stopPublishing() {
		bRunning = false;
	}
}
