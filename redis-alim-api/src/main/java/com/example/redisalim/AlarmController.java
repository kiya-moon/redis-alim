package com.example.redisalim;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class AlarmController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/alarm")
    public void sendAlarm(@RequestBody String message) {
        System.out.println("호출");
        log.info("Received alarm message: {}", message);

        // Redis에 메시지를 발행
        redisTemplate.convertAndSend("alarm-channel", message);
    }
}
