package inClassProblem6part1;

import java.util.Date;
import java.util.Random;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.naming.NamingException;

public class Writer implements Runnable {

	private boolean bRunning = false;
	private int msgCount = 0;
	private int index;
	private Connection connection = null;
	private Session session = null;
	private Queue response;
	private MessageConsumer consumer;
	private MessageProducer producer;
	public static String QUEUE = "REQUEST";
	public static String QUEUERESP = "RESPONSE";

	public Writer(int index) {
		this.index = index;
		bRunning = true;
	}

	public static void main(String[] args) {
		Thread t = new Thread(new Writer(new Random().nextInt(100)));
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
		}
	}

	public void start() {
		ConnectionFactory factory;
		try {
			factory = ConnectorFactory.getConnectionFactory();
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
			response = session.createQueue(QUEUERESP);
			consumer = session.createConsumer(response);
			producer = session.createProducer(session.createQueue(QUEUE));
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (JMSException e) {
			stop();
			e.printStackTrace();
		} catch (NamingException e) {
			stop();
			e.printStackTrace();
		}
	}

	public void run() {
		start();
		Message message;
		while (bRunning) {
			try {
				++msgCount;
				System.out.println("Writer[" + index + "].run() - message: "
						+ msgCount);
				if (msgCount % 2 == 0) {
					message = session.createTextMessage("message [" + msgCount
							+ "] at: " + new Date());
				} else {
					message = session.createMapMessage();
					((MapMessage) message).setBytes("NAME", Marshaller
							.serializeObject(new SerializableObject(msgCount,
									"foo")));
				}
				message.setJMSReplyTo(response);
				producer.send(message);
				Message reply = consumer.receive(1000);
				if (reply instanceof TextMessage) {
					System.out.println("REPLY[" + index + "]: "
							+ ((TextMessage) reply).getText());
				}
				if (bRunning) {
					Thread.sleep((int) (Math.random() * 1000));
				}
				

			} catch (Exception e) {
				e.printStackTrace();
				stop();
			}
		}
	}

	public void stop() {
		bRunning = false;
		if (connection != null) {
			try {
				connection.close();
			} catch (JMSException e) {
			}
		}
	}
}
