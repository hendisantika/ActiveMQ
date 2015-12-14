package com.santika.hendi.activeMQ;

import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

//@SpringBootApplication
//@ImportResource("classpath*:/spring/si-config.xml")
public class ActiveMqApplication {

    public static void main(String[] args) throws JMSException {
//        SpringApplication.run(ActiveMqApplication.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        jmsTemplate.send(
                new MessageCreator() {
            public ObjectMessage createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage();
                message.setObject("Haloo semuanya ....");
                return message;
            }
        });

        System.out.println("MESSAGE SENT TO myMessageQueue");
        javax.jms.Message receivedMessage = jmsTemplate.receive("myMessageQueue");
        ObjectMessage msg = (ObjectMessage) receivedMessage;
        System.out.println("Message Received :" + msg.getObject().toString());

        MessageSender messageSender = (MessageSender) context.getBean("messageSender");

        Map message = new HashMap();
        message.put("Hello", "World");
        message.put("country", "Japan");
        message.put("state", "Tokyo");
        message.put("city", "Konohagakure");

        messageSender.send(message);

        System.out.println("Message Send to Jms Queue:- " + message);
    }
}
