package com.example.hr.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {
		MongoAutoConfiguration.class, 
	    MongoDataAutoConfiguration.class,
	    MongoRepositoriesAutoConfiguration.class
})
@ConditionalOnProperty(name = "persistence.target.mongo", havingValue = "false")
public class DisableMongoAutoConfiguration {

}
