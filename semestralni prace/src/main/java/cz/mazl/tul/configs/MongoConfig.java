package cz.mazl.tul.configs;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "cz.mazl.tul.repository.mongo")
public class MongoConfig {

    @Autowired
    private MongoConfProperties mongoConfProperties;

    @Bean
    public MongoClient mongo() {
        return new MongoClient(mongoConfProperties.getHost());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        //FIXME kvůli zvýšení verze spring-boot je nyní konstruktor depricated
        return new MongoTemplate(mongo(), mongoConfProperties.getDb());
    }
}
