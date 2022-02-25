package com.polovnev.api_gateway.integrational;

import com.polovnev.api_gateway.entity.UserEntity;
import com.polovnev.api_gateway.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.polovnev.api_gateway.com.polovnev.util.FileReaderTestUtil.getRequestJson;
import static com.polovnev.api_gateway.com.polovnev.util.FileReaderTestUtil.getResponseJson;
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


    @Test
    public void given_WhenFindQuestions_ThenReturnQuestions()
            throws Exception {

        final String expectedResponseJson = getResponseJson("given_WhenFindQuestions_ThenReturnQuestions");

        Mockito.when(userService.getUsersByIds(Stream.of(1L)
                .collect(Collectors.toSet())))
                .thenReturn(Stream.of(UserEntity.builder().id(1L).username("admin").build()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequestJson("given_WhenFindQuestions_ThenReturnQuestions")))
                .andExpect(status().isOk()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponseJson, contentAsString, JSONCompareMode.STRICT);
    }

    @Test
    public void given_WhenGetQuestionById_ThenReturnQuestionById()
            throws Exception {
        final String expectedResponseJson = getResponseJson("given_WhenGetQuestionById_ThenReturnQuestionById");

        Mockito.when(userService.getUsersByIds(Stream.of(1L)
                        .collect(Collectors.toSet())))
                .thenReturn(Stream.of(UserEntity.builder().id(1L).username("admin").build()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/question/1"))
                .andExpect(status().isOk()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponseJson, contentAsString, JSONCompareMode.STRICT);
    }

}
