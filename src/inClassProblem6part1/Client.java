package inClassProblem6part1;

import java.io.IOException;
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

public class Client implements Runnable{

	private boolean bRunning = false;
	private Connection connection = null;
	private Session session = null;
	private Queue response;
	private MessageConsumer consumer;
	private MessageProducer producer;
	Reactor reactor;
	int attempt = 10;
	public static String QUEUE = "REQUEST";
	public static String QUEUERESP = "RESPONSE";
	String path;
	String fileName;

	public Client() {
		bRunning = true;
		fileName = "clientEventHandler.txt";
		path = System.getProperty("user.dir")+"\\"+fileName;
	}

	public static void main(String[] args) {
		Thread t = new Thread(new Client());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
		}
	}

	public void start() {
		ConnectionFactory factory;
		try {
			reactor = new Reactor();
			reactor.init();
			reactor.registerHandler(path);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		start();
		Message message;
		while (bRunning) {
			try {
				System.out.println("attempt left is : "+ --attempt);
				if(attempt == 0){
					stop();
				}
				message = session.createMapMessage();
				((MapMessage) message).setBytes("OBJECT", Marshaller.serializeObject("OBJECT"));
				((MapMessage) message).setString("NAME", "bob");
				message.setJMSReplyTo(response);
				message.setJMSType("Store");
				producer.send(message);
				Message reply = consumer.receive(1000);
				if(reply!=null){
					System.out.println("Message type is : "+reply.getJMSType() + "Text is: " +((TextMessage)reply).getText());
					String name = ((MapMessage)reply).getString("NAME");
					reactor.dispatch(new Event(reply.getJMSType(), name, ((TextMessage)reply).getText(), null));
				}
				if (reply instanceof TextMessage) {
					System.out.println("REPLY from server after Store: "+ ((TextMessage) reply).getText());
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
		System.out.println("Client runnable stopped");
		if (connection != null) {
			try {
				connection.close();
			} catch (JMSException e) {
			}
		}
	}
	
}

