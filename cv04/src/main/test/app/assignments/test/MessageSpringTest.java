package app.assignments.test;

import app.assignments.conf.MessageConfig;
import app.assignments.conf.WriterConfig;
import app.assignments.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MessageConfig.class, WriterConfig.class})
public class MessageSpringTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testMessage() {
        Message message = (Message) applicationContext.getBean("getHelloMessage");
        assert message.getText().compareTo("Message") == 0;
    }

    /**
     * PingMessage je označen jako Singleton
     */
    @Test
    public void testMessageScope() {
        Message message = (Message) applicationContext.getBean("getPingMessage");
        Message messageSingleton = (Message) applicationContext.getBean("getPingMessage");

        assert message == messageSingleton;
    }

    /**
     * HelloMessage je označen jako Prototype
     */
    @Test
    public void testMessageScope2() {
        Message message = (Message) applicationContext.getBean("getHelloMessage");
        Message messagePrototype = (Message) applicationContext.getBean("getHelloMessage");

        assert message != messagePrototype;
    }

    @Test
    public void testPingMessageTest() {
        Message message = (Message) applicationContext.getBean("getPingMessage");
        assert message.getText().compareTo("Ping has empty body") == 0;
    }

    @Test
    public void testReplyMessage() {
        Message message = (Message) applicationContext.getBean("getReplyMessage");
        assert message.getSender().compareTo("pong") == 0;
    }

    @Test
    public void testReplyMessageInstance() {
        Message replyMessage = (Message) applicationContext.getBean("getReplyMessage");
        Message pingMessage = (Message) applicationContext.getBean("getPingMessage");
        assert replyMessage.getRecipient().compareTo(pingMessage.getSender()) == 0;
    }
}
