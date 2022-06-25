package dev.niko.core.sentinel.server.shared.mapper;

import org.springframework.stereotype.Component;

import dev.niko.core.sentinel.server.application.app.request.AppRequest;
import dev.niko.core.sentinel.server.application.app.response.AppResponse;
import dev.niko.core.sentinel.server.domain.App;
import dev.niko.core.sentinel.server.infrastructure.app.mapping.AppMap;
import dev.niko.core.sentinel.server.shared.mapper.update.UpdateMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppMapperImpl implements AppMapper {

    private final UpdateMapper mapper;
    
    @Override
    public App toDomain(AppMap map) throws DataMapperException {
        return new App(
            map.getId(),
            map.getName(),
            map.getCurrentVersion(),
            map.getUpdateURL(),
            map.getUpdates().stream().map(u -> mapper.toDomain(u) ).toList(),
            map.getUid()
        );
    }

    @Override
    public App toDomain(AppRequest request) {
        return new App(request.name());
    }

    @Override
    public AppMap toMap(App domain) throws DataMapperException {
        String uid = null;
        if( domain.getUid() != null) {
            uid = domain.getUid().toString();
        }
        return new AppMap(
            domain.getId(),
            domain.getName(),
            domain.getCurrentVersion().value(),
            domain.getUpdateURL(),
            domain.getUpdates().stream().map( d -> mapper.toMap(d) ).toList(),
            uid
        );
    }

    @Override
    public AppResponse toReponse(App app) {
        String overview = null;
        if(app.updatesAvailable()) {
            overview = app.currentUpdateDatails().getOverview();
        }
        return AppResponse.builder()
            .uid(app.getUid().toString())
            .name(app.getName())
            .currentVersion(app.getCurrentVersion().value())
            .overview(overview)
            .updateURL(app.getUpdateURL())
            .build();
    }    
}
