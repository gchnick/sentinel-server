package dev.niko.core.sentinel.server.app;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.version.Version;

public abstract class AppMother {
    
    public static App getApp001() {
        App app = new App("Builder Tool", new Version("1.2.0"), "https://server.app/download", null, UUID.randomUUID().toString());
        app.setId(1L);
        return app;
    }

    public static App getApp002() {
        App app = new App("Coffe Delivery", new Version("2.1.14"), "https://server.app/download", null,  UUID.randomUUID().toString());
        app.setId(2L);
        return app;
    }

    public static String app001AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getApp001());
    }

    public static String App002AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getApp002());
    }
}
