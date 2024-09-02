package com.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@MessageDriven(name = "MyMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgementMode", propertyValue = "Auto-acknowledge"),
})
public class MyMDB implements MessageListener {

    private static Logger LOGGER = Logger.getLogger(MyMDB.class.getName());

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                LOGGER.info("Received Message: " + text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
