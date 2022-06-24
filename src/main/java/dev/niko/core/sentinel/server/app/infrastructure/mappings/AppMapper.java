package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import dev.niko.core.sentinel.server.app.application.AppReponse;
import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.shared.Mapper;

public interface AppMapper extends Mapper<App, AppMap> {

    AppReponse toReponse(App app);
}
