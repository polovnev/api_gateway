package com.polovnev.country.integrational;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/country")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"id\":1,\"name\":\"countryOne\"},{\"id\":2,\"name\":\"countryTwo\"}]"));
    }

    @Test
    public void given_WhenLocationsForCountryRequest_ThenReturnLocations()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/country/1/location")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"id\":1,\"name\":\"LocationOneForCountryOne\"}," +
                                "{\"id\":2,\"name\":\"LocationTwoForCountryOne\"}]"));
    }
}
