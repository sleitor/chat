package main;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by User on 12.05.2017.
 */
public class MyMessageProduccer {

    public void send(String message) {

        try {

            TopicConnection connection = (TopicConnection)createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("123Quene");
            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage textMessage = session.createTextMessage(message);

            messageProducer.send(textMessage);
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    private Connection createConnection() throws JMSException, NamingException {

            TopicConnectionFactory tcf = new ActiveMQConnectionFactory("tcp://localhost:61616");
            return tcf.createTopicConnection();

    }
}
