package com.merqury.chatgpt.rest;

import com.merqury.chatgpt.DTO.Request;
import com.merqury.chatgpt.DTO.Response;
import com.merqury.chatgpt.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/api/gpt")
    private Response get(@RequestBody Request request) throws Exception {
        String str = mainService.getResponse(request.getPrompt());
        return Response.builder()
                .length(str.length())
                .payload(str)
                .response_code(200)
                .build();
    }
}
