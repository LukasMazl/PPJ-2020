package app.assignments.conf;

import app.assignments.writer.ListWriter;
import app.assignments.writer.StdoutWriter;
import app.assignments.writer.Writer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WriterConfig {

    @Bean
    public Writer getStdoutWriter() {
        return new StdoutWriter();
    }

    @Bean
    public Writer getListWriter() {
        return new ListWriter();
    }
}
