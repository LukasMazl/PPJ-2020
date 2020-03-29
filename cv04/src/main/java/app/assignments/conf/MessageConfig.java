package app.assignments.conf;

import app.assignments.message.CustomMessage;
import app.assignments.message.Message;
import app.assignments.message.PingMessage;
import app.assignments.message.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MessageConfig {

    @Autowired
    @Qualifier("getPingMessage")
    private Message message;

    @Bean
    public PingMessage getPingMessage() {
        return new PingMessage();
    }

    @Bean
    @Scope("prototype")
    public CustomMessage getHelloMessage() {
        return new CustomMessage("Lukas", "Jan", "Message");
    }

    @Bean
    public ReplyMessage getReplyMessage() {
        return new ReplyMessage(message, "Reply");
    }


}
