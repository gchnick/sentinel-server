package dev.niko.core.sentinel.server.shared.mapper;

import dev.niko.core.sentinel.server.application.app.request.AppRequest;
import dev.niko.core.sentinel.server.application.app.response.AppResponse;
import dev.niko.core.sentinel.server.domain.App;
import dev.niko.core.sentinel.server.infrastructure.app.mapping.AppMap;

public interface AppMapper extends Mapper<App, AppMap> {

    AppResponse toReponse(App app);

    App toDomain(AppRequest request);
}
