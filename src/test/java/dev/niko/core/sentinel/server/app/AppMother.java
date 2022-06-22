package dev.niko.core.sentinel.server.app;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.niko.core.sentinel.server.version.Version;

public abstract class AppMother {

    public static AppDTO getAppDTO001() {
        return new AppDTO("Builder Tool", "1.2.0", "https://server.app/download");
    }

    public static AppDTO getUpdateAppDTO001() {
        return new AppDTO( "Coffe Delivery", "1.2.0", "https://server.coffe/download");
    }

    public static AppDTO getAppDTO002() {
        return new AppDTO( "Coffe Delivery", "2.1.14", "https://server.app/download");
    }
    
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

    public static String appDTO001AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getAppDTO001());
    }

    public static String newApp002AsJson() throws Exception {
        return new ObjectMapper().writeValueAsString(getAppDTO002());
    }

    public static String toJson(AppDTO app) throws Exception {
        return new ObjectMapper().writeValueAsString(app);
    }
}
