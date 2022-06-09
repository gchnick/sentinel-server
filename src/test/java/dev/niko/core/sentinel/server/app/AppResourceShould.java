package dev.niko.core.sentinel.server.app;

import static dev.niko.core.sentinel.server.app.AppMother.getApp001;
import static dev.niko.core.sentinel.server.app.AppMother.toJson;
import static dev.niko.core.sentinel.server.app.AppMother.getUpdateAppDTO001;
import static dev.niko.core.sentinel.server.app.AppMother.appDTO001AsJson;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.hamcrest.Matchers.containsString;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
        App app = getApp001();
        AppDTO appDTO = AppMother.getAppDTO001();
        String mockContent = appDTO001AsJson();
        when(appService.create(appDTO)).thenReturn(app);

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
        
        AppDTO dto = getUpdateAppDTO001();
        String mockContent = toJson(dto);

        App updatedApp = new App(dto.name(), dto.currentVersion(), dto.updateURL());
        updatedApp.setId(UUID.fromString(uidApp001));
        when(appService.update(uidApp001, dto)).thenReturn(updatedApp);

        // When
        ResultActions result = mvc.perform(put(URL_TEMPLATE.concat(mockPathVariable), uidApp001)
         .contentType(APPLICATION_JSON)
         .content(mockContent));

        // Then
        result.andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.data.app").exists());
    }

    @Test
    void get_app_by_id() throws Exception {
        // Given
        String mockPathVariable = "/{uid}";
        App mockApp001 = getApp001();
        String uidApp001 = mockApp001.getId().toString();
        when(appService.get(uidApp001)).thenReturn(mockApp001);

        // When
        ResultActions result = mvc.perform(get(URL_TEMPLATE.concat(mockPathVariable), uidApp001)
            .contentType(APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.data.app").exists());

        verify(appService).get(anyString());
    }
}
