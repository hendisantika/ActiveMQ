/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santika.hendi.activeMQ;

import org.springframework.jms.core.JmsTemplate;

/**
 *
 * @author hendi.santika
 */
public class MessageSender {

    private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final Object Object) {
        jmsTemplate.convertAndSend(Object);
    }

}
