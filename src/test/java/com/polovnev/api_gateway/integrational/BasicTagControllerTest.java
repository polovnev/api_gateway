package com.polovnev.api_gateway.integrational;


import com.polovnev.api_gateway.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.polovnev.api_gateway.com.polovnev.util.FileReaderTestUtil.getResponseJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.polovnev:question_response:+:stubs:8081")
public class BasicTagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private UserService userService;


    @WithMockUser
    @Test
    public void given_WhenGetAllTags_ThenReturnAllTags()
            throws Exception {

        final String expectedResponseJson = getResponseJson("given_WhenGetAllTags_ThenReturnAllTags");

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/tag"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponseJson, contentAsString, JSONCompareMode.STRICT);

    }

}
