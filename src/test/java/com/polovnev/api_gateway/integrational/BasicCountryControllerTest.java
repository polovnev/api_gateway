package com.polovnev.api_gateway.integrational;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
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
        ids = "com.polovnev:location:+:stubs:8000")
public class BasicCountryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void given_WhenCountriesRequest_ThenReturnCountries()
            throws Exception {

        final String expectedResponseJson = getResponseJson("given_WhenCountriesRequest_ThenReturnCountries");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/country"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponseJson, contentAsString, JSONCompareMode.STRICT);

    }

    @Test
    public void given_WhenLocationsForCountryRequest_ThenReturnLocations()
            throws Exception {

        final String expectedResponseJson = getResponseJson("given_WhenLocationsForCountryRequest_ThenReturnLocations");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/country/1/location"))
                .andExpect(status().isOk()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponseJson, contentAsString, JSONCompareMode.STRICT);

    }
}
