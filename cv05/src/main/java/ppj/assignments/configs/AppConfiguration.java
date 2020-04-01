package ppj.assignments.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {

    @Autowired
    private Environment environment;

    @NotNull
    private String desc;

    @NotNull
    private String name;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String toString() {
        return String.format("Application %s [profiles %s, description '%s']", name, String.join(",", Arrays.asList(environment.getActiveProfiles())), desc);
    }
}