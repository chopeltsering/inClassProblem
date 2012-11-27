package inClassProblem6part1;

import java.io.IOException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.NamingException;

public class VaultServer implements Runnable {

	private boolean bRunning;
	private Connection connection = null;
	private Session session;
	private MessageConsumer consumer;
	MessageProducer TopicProducer;
	Reactor reactor;
	Vault vault;
	String path;
	String fileName = "serverEventHandler.txt"; 
	int totalMsg = 0;
	public static String TOPIC = "Services";

	public VaultServer() {
		bRunning = true;
		vault = new Vault();
		reactor = Reactor.getInstance();
		path = System.getProperty("user.dir")+"\\"+fileName;
	}

	public static void main(String[] args) {
		Thread t = new Thread(new VaultServer());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
		}
	}

	public void start() {
		ConnectionFactory factory;
		try {
			reactor.init();
			reactor.registerHandler(path);
			factory = ConnectorFactory.getConnectionFactory();
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
			Topic topic = session.createTopic(TOPIC);
			TopicProducer = session.createProducer((Destination) topic);
			consumer = session.createConsumer(session.createQueue(Client.QUEUE));
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
		System.out.println("inside run");
		start();
		System.out.println("after start");
		while (bRunning) {
			try {
				Message msg = consumer.receive(1000);
				if(msg!=null){
					System.out.println("total message received so far is : "+ ++totalMsg);
					String name = ((MapMessage)msg).getString("NAME");
					reactor.dispatch(new Event(msg.getJMSType(),name, "", Marshaller.deserializeObject(((MapMessage)msg).getBytes("OBJECT"))));
					
					TextMessage textMsg = session.createTextMessage("current time in hours : " + System.currentTimeMillis()/1000*60*60);
					TopicProducer.send(textMsg);
					MessageProducer producer = session.createProducer(msg.getJMSReplyTo());
					producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
					TextMessage message = session.createTextMessage(""+name + ": OK");
					message.setJMSType("StoreResponse");
					producer.send(message);
				}
				
			} catch (Exception e) {
				System.out.println("exception caught");
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
