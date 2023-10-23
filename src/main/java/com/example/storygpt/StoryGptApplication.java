package com.example.storygpt;

import io.github.asleepyfish.annotation.EnableChatGPT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableChatGPT
public class StoryGptApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoryGptApplication.class, args);
    }

}
