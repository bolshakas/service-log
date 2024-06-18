package com.example.servicelog.controller;

import com.example.servicelog.dto.ShuffleRequest;
import com.example.servicelog.service.AsyncLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.servicelog.util.Constants.LOG_ENDPOINT;

@RestController
@RequestMapping(LOG_ENDPOINT)
@Slf4j
public class LogController {

    @Autowired
    private AsyncLogService asyncLogService;

    @PostMapping
    public ResponseEntity<Void> log(@RequestBody ShuffleRequest request) {
        asyncLogService.logAndValidateRequestAsync(request);
        return ResponseEntity.ok().build();
    }
}

