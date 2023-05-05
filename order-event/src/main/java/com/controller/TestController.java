package com.controller;
import com.dto.EcmDto;
import com.service.SnsService;
import com.service.SqsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final SnsService snsService;
    private final SqsService sqsService;

    //메세지 발행 메소드
    @PostMapping("/publish")
    public void publish(@RequestBody Map<String, Object> scriptData) {
        PublishResponse pr = snsService.awsSnsPublishTest(scriptData);

    }

    //메세지 가져오는 메소드
    @GetMapping("/subscribe")
    public void getMessage() {
        sqsService.getMessage();
    }


}
