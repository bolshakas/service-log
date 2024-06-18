package com.example.servicelog.controller;

import com.example.servicelog.dto.ShuffleRequest;
import com.example.servicelog.exeption.GlobalExceptionHandler;
import com.example.servicelog.service.AsyncLogService;
import com.example.servicelog.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.servicelog.util.Constants.CONTENT_TYPE;
import static com.example.servicelog.util.Constants.LOG_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LogController.class)
@Import(GlobalExceptionHandler.class)
public class LogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AsyncLogService asyncLogService;

    @Test
    public void testLog() throws Exception {
        ShuffleRequest request = new ShuffleRequest();
        request.setNumber(5);

        mockMvc.perform(post(LOG_ENDPOINT)
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testLogBadRequest() throws Exception {
        ShuffleRequest request = new ShuffleRequest();
        request.setNumber(0);

        mockMvc.perform(post(LOG_ENDPOINT)
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}

