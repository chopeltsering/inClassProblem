package inClassProblem6part2;

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

public class Client implements MessageListener, Runnable {

	private Connection connection;
	private boolean bRunning;

	public Client() {
		connection = null;
		bRunning = true;
	}

	public static void main(String[] args) {
		Client s = new Client();
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
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(Publisher.TOPIC);
			MessageConsumer consumer = session.createConsumer((Destination) topic);
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
				System.out.println("Subscriber onMessage(): "+ txtMsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	public void stopListening() {
		System.out.println("stop listening has been called from client");
		try {
			connection.close();
			bRunning = false;
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		startListening();
		while (bRunning) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Subscriber is still alive");
		}
	}
}
