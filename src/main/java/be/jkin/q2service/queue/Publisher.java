package be.jkin.q2service.queue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public String SendMessageToQueue(String message)
    {
        amqpTemplate.convertAndSend("kudos-queue", message);
        return "Message sent to Queue!";
    }
}
