package dev.niko.core.sentinel.server.app;

import static dev.niko.core.sentinel.server.app.AppMother.getApp001;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import dev.niko.core.sentinel.server.app.application.AppResource;
import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.AppService;

@WebMvcTest(AppResource.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
public class AppResourceShould {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppService appService;

    private String URL_TEMPLATE = "/api/v1/apps";

    @Test
    void save_new_app() throws Exception {
        // Given
        String appName = "Builder Tool";
        String mockContent = String.format("{ \"name\": \"%s\" }", appName);
        App app = getApp001();
        when(appService.create(appName)).thenReturn(app);

        // When
        ResultActions result = mvc.perform(post(URL_TEMPLATE)
            .content(mockContent)
            .contentType(APPLICATION_JSON));

        // Then
        String expectedLocationUid = "/".concat(app.getId().toString());
        result.andExpect(status().isCreated())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(header().string("Location", containsString(URL_TEMPLATE.concat(expectedLocationUid))))
            .andExpect(jsonPath("$.data.app").exists());
    }

    @Test
    void update_app_by_id() throws Exception {
        // Given
        String mockPathVariable = "/{uid}";
        App app001 = getApp001();
        String uidApp001 = app001.getId().toString();
        
        String updateAppName = "Coffe Delivery";
        String mockContent = String.format("{ \"name\": \"%s\" }", updateAppName);

        // When
        ResultActions result = mvc.perform(put(URL_TEMPLATE.concat(mockPathVariable), uidApp001)
         .contentType(APPLICATION_JSON)
         .content(mockContent));

        // Then
        result.andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void get_app_by_id() throws Exception {
        // Given
        String mockPathVariable = "/{uid}";
        App mockApp001 = getApp001();
        UUID uidApp001 = UUID.fromString(mockApp001.getUid());
        when(appService.get(uidApp001)).thenReturn(mockApp001);

        // When
        ResultActions result = mvc.perform(get(URL_TEMPLATE.concat(mockPathVariable), uidApp001)
            .contentType(APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.data.app").exists());

        verify(appService).get(any(UUID.class));
    }
}
