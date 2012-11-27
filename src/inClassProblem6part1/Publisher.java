package inClassProblem6part1;

import java.util.Date;
import java.util.Random;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Publisher implements Runnable {

	private boolean bRunning = false;
	private int msgCount = 0;
	private int index;
	public static String TOPIC = "Services";

	public Publisher(int index) {
		this.index = index;
	}

	public static void main(String[] args) {
		int index = new Random().nextInt(100);
		Publisher p = new Publisher(index);
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
				System.out.println("Publish[" + index + "].run() - message: "
						+ msgCount);

				ConnectionFactory factory = ConnectorFactory
						.getConnectionFactory(Broker.SERVICE);
				connection = factory.createConnection();
				connection.start();

				Session session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
				javax.jms.Topic topic = session.createTopic(TOPIC);
				System.out.println("Publish[" + index + "]" + topic);

				MessageProducer producer = session
						.createProducer((Destination) topic);
				producer.setTimeToLive(10000);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				TextMessage message = session
						.createTextMessage("Here is a message [" + msgCount
								+ "] at: " + new Date());
				producer.send(message);

				connection.close(); // In a real world application, you may want
				// to keep
				connection = null; // the connection open for performance.

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
