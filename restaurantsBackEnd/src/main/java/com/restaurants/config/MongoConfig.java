package com.restaurants.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//@Configuration
public class MongoConfig {

    /*@Autowired
    private Environment env;*/

    /*@Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.uri")));
    }*/

    /*@Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        return mongoTemplate;

    }*/

}