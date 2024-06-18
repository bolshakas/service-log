package com.example.servicelog.service;

import com.example.servicelog.dto.ShuffleRequest;
import com.example.servicelog.exeption.InvalidNumberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.example.servicelog.util.Constants.*;

@Service
@Slf4j
public class AsyncLogService {

    @Async
    public void logAndValidateRequestAsync(ShuffleRequest request) {
        int number = request.getNumber();
        if (number < 1 || number > 1000) {
            log.warn(INVALID_REQUEST_LOG, request);
            throw new InvalidNumberException(NUMBER_VALIDATION_MESSAGE);
        } else {
            log.info(VALID_REQUEST_LOG, request);
        }
    }
}
