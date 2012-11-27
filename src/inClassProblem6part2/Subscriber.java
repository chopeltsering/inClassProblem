package inClassProblem6part2;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;

public class Subscriber implements MessageListener, Runnable {

	private Connection connection;
	private int index;
	private boolean bRunning;

	public Subscriber(int index) {
		this.index = index;
		connection = null;
		bRunning = true;
	}

	public static void main(String[] args) {
		int index = new Random().nextInt(100);
		Subscriber s = new Subscriber(index);
		Thread t = new Thread(s);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startListening() {
		System.out.println("Started listening");

		try {
			ConnectionFactory factory = ConnectorFactory.getConnectionFactory(Broker.SERVICE);
			connection = factory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(Publisher.TOPIC);
			MessageConsumer consumer = session
					.createConsumer((Destination) topic);
			consumer.setMessageListener(this);

			connection.start();
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.toString());
		}
	}

	/**
	 * Just log a note when a message is received.
	 */
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage txtMsg = (TextMessage) message;
			try {
				System.out.println("Subscriber[" + index + "].onMessage(): "
						+ txtMsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopListening() {
		try {
			connection.close();
			bRunning = false;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		startListening();
		while (bRunning) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Subscriber [" + index + "] is still alive");
		}
	}
}
