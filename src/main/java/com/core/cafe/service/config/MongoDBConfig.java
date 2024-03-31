package com.core.cafe.service.config;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {



    @Value("${mongo.host}")
    private String mongoHost;


    @Value("${cafe.order.mongo.database}")
    private String mongoDatabase;

    @Bean
    public MongoClient mongoClient(){
        MongoClient mongoClient = null;
        try {
            ConnectionString connectionString = new ConnectionString(mongoHost + mongoDatabase);
            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            mongoClient = MongoClients.create(mongoClientSettings);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return mongoClient;
    }


    @Bean
    public MongoTemplate mongoTemplate(){
        MongoTemplate mongoTemplate = null;
        try {
            mongoTemplate = new MongoTemplate(mongoClient(), mongoDatabase);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return mongoTemplate;

    }
}
