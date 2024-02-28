package com.tdatingapp.chatbot;

import com.tdatingapp.chatbot.config.BotConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ChatConfigurer {

    @Bean
    @ConfigurationProperties("bot")
    public BotConfig botConfig() {
        return new BotConfig();
    }

}
