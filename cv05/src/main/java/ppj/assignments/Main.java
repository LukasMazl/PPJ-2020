package ppj.assignments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ppj.assignments.configs.AppConfiguration;
import ppj.assignments.writer.Writer;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        AppConfiguration cfg = ctx.getBean(AppConfiguration.class);
        Writer writer = ctx.getBean(Writer.class);
        writer.write(cfg.toString());
    }

}