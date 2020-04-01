package ppj.assignments.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ppj.assignments.Main;

public class DevelWriter implements Writer {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Override
    public void write(String message) {
        log.debug(message);
    }
}
