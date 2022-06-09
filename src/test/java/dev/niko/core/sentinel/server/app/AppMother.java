package dev.niko.core.sentinel.server.app;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.niko.core.sentinel.server.version.Version;

public abstract class AppMother {

    public static App getNewApp001() {
        return new App("Builder Tool", new Version("1.2.0"), "https://server.app/download");
    }

    public static App getNewApp002() {
        return new App("Coffe Delivery", new Version("2.1.14"), "https://server.app/download");
    }
    
    public static App getApp001() {
        App app = new App("Builder Tool", new Version("1.2.0"), "https://server.app/download");
        app.setId(UUID.randomUUID());
        return app;
    }

    public static App getApp002() {
        App app = new App("Coffe Delivery", new Version("2.1.14"), "https://server.app/download");
        app.setId(UUID.randomUUID());
        return app;
    }

    public static String newApp001AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getNewApp001());
    }

    public static String newApp002AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getNewApp002());
    }

    public static String getAppAsJson(App app) throws Exception {
        return new ObjectMapper().writeValueAsString(app);
    }
}
