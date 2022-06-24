package dev.niko.core.sentinel.server.app.shared.mapper;

import dev.niko.core.sentinel.server.app.application.AppReponse;
import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.AppMap;

public interface AppMapper extends Mapper<App, AppMap> {

    AppReponse toReponse(App app);
}
