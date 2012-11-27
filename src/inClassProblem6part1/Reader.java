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
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.naming.NamingException;

public class Reader implements Runnable {

	private int index;
	private boolean bRunning;
	private Connection connection = null;
	private Session session;
	private MessageConsumer consumer;

	public Reader(int index) {
		this.index = index;
		bRunning = true;
	}

	public static void main(String[] args) {
		Thread t = new Thread(new Reader(new Random().nextInt(100)));
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
			consumer = session.createConsumer(session.createQueue(Writer.QUEUE));
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
		while (bRunning) {
			try {
				Message msg = consumer.receive(1000);
				if (msg instanceof TextMessage) {
					System.out.println("Reader[" + index + "]: "
							+ ((TextMessage) msg).getText());
				} else if (msg instanceof MapMessage) {
					Object o = Marshaller.deserializeObject(((MapMessage)msg).getBytes("NAME"));
					System.out.println("Reader[" + index + "]: " + o);
				} else if(msg instanceof ObjectMessage){
					
				}
				MessageProducer producer = session.createProducer(msg.getJMSReplyTo());
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				TextMessage message = session.createTextMessage("Reader ["
						+ index + "] got message at: " + new Date());
				producer.send(message);
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
