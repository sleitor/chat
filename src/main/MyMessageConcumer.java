package main;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by User on 12.05.2017.
 */
public class MyMessageConcumer {

    private Connection createConnection() throws JMSException {
//        ActiveMQConnectionFactory activeMQConnectionFactory =
//                new ActiveMQConnectionFactory("tcp://localhost:61616");
//        return activeMQConnectionFactory.createConnection();

        TopicConnectionFactory tcf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        return tcf.createTopicConnection();

    }

    public void reseive(){

        try {

            TopicConnection connection = (TopicConnection)createConnection();
            connection.start();
            TopicSession session = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
            Topic destination = session.createTopic("123Quene");

            MessageConsumer messageConsumer = session.createSubscriber(destination);



            while (true) {
                Message message = messageConsumer.receive(3000);
                System.out.println(((TextMessage) message).getText());
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }



}
