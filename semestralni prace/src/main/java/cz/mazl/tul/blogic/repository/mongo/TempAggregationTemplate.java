package cz.mazl.tul.blogic.repository.mongo;

import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import org.apache.tomcat.jni.File;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class TempAggregationTemplate {
    private MongoTemplate mongoOperations;

    public TempAggregationTemplate(MongoTemplate mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public double getAvgTempleForCity(String city, String country) {
        TypedAggregation<TemperatureEntity> aggregation = newAggregation(TemperatureEntity.class,
                match(Criteria.where("countryIso").is(country).and("city").is(city)),
                group("temp").avg("temp").as("avgTemp")
        );
        AggregationResults<TemperatureEntity> results = mongoOperations.aggregate(aggregation, TemperatureEntity.class);
        List<Document> arrayList = (ArrayList) results.getRawResults().get("results");
        Document statsDoc = arrayList.get(0);

        return (double) statsDoc.get("avgTemp");
    }
}
