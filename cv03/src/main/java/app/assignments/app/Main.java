package app.assignments.app;

import app.assignments.message.CustomMessage;
import app.assignments.message.Message;
import app.assignments.message.PingMessage;
import app.assignments.message.ReplyMessage;
import app.assignments.writer.ListWriter;
import app.assignments.writer.StdoutWriter;
import app.assignments.writer.Writer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {


        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context-beans.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");


        Message ping = applicationContext.getBean(PingMessage.class);
        Message hello = applicationContext.getBean(CustomMessage.class);
        Message pingReply = applicationContext.getBean(ReplyMessage.class);

        Writer stdoutWriter = applicationContext.getBean(StdoutWriter.class);
        Writer listWriter = applicationContext.getBean(ListWriter.class);
        stdoutWriter.write(ping);
        stdoutWriter.write(hello);
        stdoutWriter.write(pingReply);


        listWriter.write(ping);
        ((ListWriter) listWriter).listWrittenMessages().forEach(stdoutWriter::write);

    }

}
