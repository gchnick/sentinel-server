package dev.niko.core.sentinel.server.app;

public interface AppService {

    App create(App app);

    App get(String uid);

    App update(String uid, App appUpdated);

    void delete(String uid);
}
