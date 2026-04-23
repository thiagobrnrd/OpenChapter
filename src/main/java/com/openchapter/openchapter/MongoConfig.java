package com.openchapter.openchapter;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MongoConfig {

    private static final String URI = "mongodb+srv://thiagobrnrd25:Thb291020@cluster0.lplotlh.mongodb.net/openchapter?appName=Cluster0&connectTimeoutMS=30000&serverSelectionTimeoutMS=30000";

    @Bean
    public MongoClient mongoClient() {
        System.setProperty("jdk.net.hosts.file", "");
        System.setProperty("sun.net.spi.nameservice.nameservers", "8.8.8.8,8.8.4.4");
        System.setProperty("sun.net.spi.nameservice.provider.1", "dns,sun");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(URI))
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "openchapter");
    }
}