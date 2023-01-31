package com.smuraha.notification;

import com.smuraha.amqp.RabbitMqMessageProducer;
import com.smuraha.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.smuraha.notification",
                "com.smuraha.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.smuraha.clients"
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMqMessageProducer producer,
            NotificationConfig notificationConfig
    ){
        return args -> {
            producer.publish(
                    new Person("Lexa",23),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey()
            );
        };
    }
    record Person(String name,int age){}
}
