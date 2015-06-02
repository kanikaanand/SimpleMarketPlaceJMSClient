import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSClient  {
	private Connection connection;
	private Session session;
	private Topic adTopic;
	private Queue queue;
	private MessageConsumer consumer;
	private Topic adReplyTopic;
	private Queue replyQueue;


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public JMSClient()
	{
		try
		{
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
			properties.put(Context.PROVIDER_URL, "localhost");

			InitialContext jndi = new InitialContext(properties);
			ConnectionFactory conFactory = (ConnectionFactory)jndi.lookup("XAConnectionFactory");
			connection = conFactory.createConnection();

			session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );
			queue = (Queue)jndi.lookup("UserQueue");
			try
			{
				adTopic = (Topic)jndi.lookup("adTopic");
			}
			catch(NamingException NE1)
			{
				System.out.println("NamingException: "+NE1+ " : Continuing anyway...");
			}

			if( null == adTopic )
			{
				adTopic = session.createTopic("adTopic");
				jndi.bind("adTopic", adTopic);
			}

			/*	consumer = session.createConsumer( topic );


		consumer.setMessageListener(this);*/
			connection.start();
			System.out.println("connection started on client side ");


		}
		catch(Exception e)
		{

		}
	}


	public String signIn(String username, String password) throws JMSException{
		String userString;
		String adString;
		MessageProducer MP = session.createProducer(queue);

		TextMessage TM = session.createTextMessage("signin,"+username+","+password);

		replyQueue = session.createTemporaryQueue();		
		consumer = session.createConsumer(replyQueue);

		TM.setJMSReplyTo(replyQueue);
		System.out.println("In JMSclient");
		MP.send(TM);


		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Back from server");

		userString = (Reply.getText());
		//String []userTokens = userString.split(";");
		//System.out.println("user string is"+userString);
		//adString = getUserAds(Integer.parseInt(userTokens[0]));
		return userString;

	}
	public String signUp(String firstName, String lastName, String username, String password) throws JMSException{
		String userString;
		MessageProducer MP = session.createProducer(queue);
		TextMessage TM = session.createTextMessage("signup,"+firstName+","+lastName+","+username+","+password);

		replyQueue = session.createTemporaryQueue();		
		consumer = session.createConsumer(replyQueue);

		TM.setJMSReplyTo(replyQueue);
		MP.send(TM);

		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Back from JMS SERVER : in JMS CLIENT Now");
		userString = Reply.getText();
		return userString;

	}
	public void postAd(int userId, String itemName, String itemDesc, String sellerInfo, String itemPrice) throws JMSException{
		String itemString;
		MessageProducer MP = session.createProducer(queue);
		TextMessage TM = session.createTextMessage("postad,"+userId+","+itemName+","+itemDesc+","+sellerInfo+","+itemPrice);

		//replyQueue = session.createTemporaryQueue();		
		//consumer = session.createConsumer(replyQueue);

		//TM.setJMSReplyTo(replyQueue);
		MP.send(TM);		
	}		
	public String getUserAds(int userId) throws JMSException
	{
		System.out.println("JMS client: getUserAds()");
		String adString="";
		MessageProducer MP = session.createProducer(adTopic);
		TextMessage TM = session.createTextMessage("getUserAds,"+userId);

		adReplyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( adReplyTopic );


		TM.setJMSReplyTo(adReplyTopic);
		System.out.println("Sending request................ to topic");
		MP.send(TM);
		TextMessage Reply = (TextMessage)consumer.receive();

		adString =  Reply.getText();
		System.out.println(" In JMS Client : Adstring is "+ adString);
		return adString;
	}

	public boolean subscribeUser(int userId) throws JMSException
	{
		MessageProducer MP = session.createProducer(queue);
		TextMessage TM = session.createTextMessage("subscribe,"+userId);
		replyQueue = session.createTemporaryQueue();		
		consumer = session.createConsumer(replyQueue);
		TM.setJMSReplyTo(replyQueue);
		System.out.println("In JMSclient");
		MP.send(TM);


		TextMessage Reply = (TextMessage)consumer.receive();
		String subscriptionString = (Reply.getText());
		//String []userTokens = userString.split(";");
		if(subscriptionString.equalsIgnoreCase("subscribed successfully"))
			return true;
		else 
			return false;

	}
	public boolean unsubscribeUser(int userId) throws JMSException
	{
		MessageProducer MP = session.createProducer(queue);
		TextMessage TM = session.createTextMessage("unsubscribe,"+userId);
		replyQueue = session.createTemporaryQueue();		
		consumer = session.createConsumer(replyQueue);
		TM.setJMSReplyTo(replyQueue);
		System.out.println("In JMSclient");
		MP.send(TM);


		TextMessage Reply = (TextMessage)consumer.receive();
		String subscriptionString = (Reply.getText());
		//String []userTokens = userString.split(";");
		if(subscriptionString.equalsIgnoreCase("unsubscribed successfully"))
			return true;
		else 
			return false;

	}


}
