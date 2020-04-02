package cz.mazl.tul.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "cz.mazl.tul.hibernate")
public class HibernateProperties {
    @NotNull
    private String dialect;

    private String show_sql;



    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}
