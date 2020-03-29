package app.assignments.writer;

import app.assignments.message.Message;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListWriter implements Writer {

    private List<Message> buffer;

    @PostConstruct
    private void init() {
        buffer = new ArrayList<>();
    }

    public void write(Message message) {
        buffer.add(message);
    }

    public List<Message> listWrittenMessages() {
        return Collections.unmodifiableList(buffer);
    }
}
