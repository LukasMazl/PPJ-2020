package cz.mazl.tul.configs;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("prod")
@EnableMongoRepositories(basePackages = "cz.mazl.tul.repository.mongo")
public class MongoConfig {

    @Bean
    public MongoClient mongo() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "test");
    }
}
