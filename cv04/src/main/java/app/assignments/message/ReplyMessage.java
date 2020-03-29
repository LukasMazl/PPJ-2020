package app.assignments.message;

import javax.annotation.PostConstruct;

public class ReplyMessage implements Message {
    public static int INSTANCE_COUNT = 0;

    private Message original;
    private String reply;

    public ReplyMessage(Message original, String reply) {
        this.original = original;
        this.reply = reply;
    }

    @PostConstruct
    public void init() {
        ReplyMessage.INSTANCE_COUNT++;
        System.out.println("Creating new instance.");
    }

    public String getSender() {
        return original.getRecipient();
    }

    public String getRecipient() {
        return original.getSender();
    }

    public String getText() {
        return "ORIGINAL:\n" + original.getText() + "\nREPLY:\n" + reply;
    }
}
