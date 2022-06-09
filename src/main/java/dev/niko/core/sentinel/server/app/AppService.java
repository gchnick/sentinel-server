package dev.niko.core.sentinel.server.app;

public interface AppService {

    App create(AppDTO dto);

    App get(String uid);

    App update(String uid, AppDTO dto);

    void delete(String uid);
}
