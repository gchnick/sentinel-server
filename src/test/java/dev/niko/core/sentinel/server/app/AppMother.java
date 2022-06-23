package dev.niko.core.sentinel.server.app;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.version.Version;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.AppMap;

public abstract class AppMother {
    
    public static App getApp001() {
        App app = new App("Builder Tool", new Version("1.2.0"), "https://server.app/download", null);
        app.setId(1L);
        app.setUid(UUID.randomUUID());
        return app;
    }

    public static AppMap getAppMap001() {
        AppMap app = new AppMap(1L, "Builder Tool", "1.2.0", "https://server.app/download", null, UUID.randomUUID().toString());
        return app;
    }

    public static AppMap getAppMap002() {
        AppMap app = new AppMap(2L, "Coffe Delivery", "2.1.14", "https://server.app/download", null, UUID.randomUUID().toString());
        return app;
    }

    public static App getApp002() {
        App app = new App("Coffe Delivery", new Version("2.1.14"), "https://server.app/download", null);
        app.setId(2L);
        app.setUid(UUID.randomUUID());
        return app;
    }

    public static String app001AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getApp001());
    }

    public static String App002AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getApp002());
    }
}
