package cz.mazl.tul.configs.repository;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "cz.mazl.tul.blogic.repository.mongo")
public class MongoConfig {

    @Autowired
    private MongoConfProperties mongoConfProperties;

    @Bean
    public MongoClient mongo() {
        return new MongoClient(mongoConfProperties.getHost());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), mongoConfProperties.getDb());
    }
}
