package com.polovnev.api_gateway.integrational;

import com.polovnev.api_gateway.entity.UserEntity;
import com.polovnev.api_gateway.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.polovnev:question_response:+:stubs:8081")
public class BasicQuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void given_WhenFindQuestions_ThenReturnQuestions()
            throws Exception {
        Mockito.when(userService.getUsersByIds(Stream.of(1L)
                .collect(Collectors.toSet())))
                .thenReturn(Stream.of(UserEntity.builder().id(1L).username("admin").build()));
        mockMvc.perform(MockMvcRequestBuilders.post("/question/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"locationId\": 1\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"id\":1,\"ratePoints\":null,\"text\":\"First question?\",\"author\":1,\"authorName\":\"admin\",\"location\":1,\"isResponded\":true},{\"id\":2,\"ratePoints\":null,\"text\":\"Second question?\",\"author\":1,\"authorName\":\"admin\",\"location\":1,\"isResponded\":false}]"));
    }

}
