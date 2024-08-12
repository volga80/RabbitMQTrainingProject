package com.volga.TrainingProjectRabbitMQ.controller;

import com.volga.TrainingProjectRabbitMQ.service.PublicationsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NewPostController {

    private final PublicationsService publicationsService;

    @PostMapping("newPost")
    public ResponseEntity<String> createNewPost(@RequestBody PostRequest postRequest) {
        publicationsService.createPublication(postRequest.getPostContent(), postRequest.getUserId());
        return ResponseEntity.ok("Пост успешно создан");
    }

    @Data
    public static class PostRequest {
        private String postContent;
        private String userId;
    }
}
