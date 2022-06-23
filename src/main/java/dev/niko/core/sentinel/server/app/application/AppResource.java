package dev.niko.core.sentinel.server.app.application;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

import java.io.File;
import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.AppService;
import dev.niko.core.sentinel.server.app.domain.exception.ConflictException;
import dev.niko.core.sentinel.server.app.domain.update.Update;
import dev.niko.core.sentinel.server.app.domain.version.validation.Version;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/apps")
@RequiredArgsConstructor
public class AppResource {

    private static final String ERROR_UPLOADS = "Internal server error when uploading file. ";
    
    private final AppService appService;

    @Value("${config.uploads.path}")
    private String path;

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody @Valid NewApp app) {
        UUID uid = appService.create(app.name());
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{uid}")
            .buildAndExpand(uid.toString())
            .toUri();
        return ResponseEntity.created(uri).body(
            Response.builder()
            .timeStamp(now())
            .status(CREATED)
            .statusCode(CREATED.value())
            .build()
        );
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> get(@PathVariable UUID uid) {
        return ResponseEntity.ok(
            appService.get(uid)
        );
    }

    @PostMapping("/{uid}/name")
    public ResponseEntity<Response> edit(UUID uid, @Valid NewApp app) {
        appService.setName(uid, app.name());
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(now())
            .status(OK)
            .statusCode(OK.value())
            .build()
        );
    }

    @PostMapping("/{uid}/release")
    public ResponseEntity<Response> releaseUpdate(UUID uid, @Valid NewUpdate update, @RequestParam(required = true) MultipartFile file) {
        App app = appService.get(uid);
        String filename = fixFilename(file.getOriginalFilename());

        try {
            file.transferTo(new File(path.concat(filename)));
            app.setUpdateURL(filename);
        } catch (Exception e) {
            throw new ConflictException(ERROR_UPLOADS.concat(e.getMessage()));
        }

        Update _update = new Update(update.version(), update.overview());
        
        UUID createdUid = appService.releaseUpdate(app, _update);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{uid}/release/{uid}")
            .buildAndExpand(app.getUid().toString(), createdUid.toString())
            .toUri();
        return ResponseEntity.created(uri).body(
            Response.builder()
            .timeStamp(now())
            .status(CREATED)
            .statusCode(CREATED.value())
            .build()
        );
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(UUID uid) {
        appService.delete(uid);
        return ResponseEntity.noContent().build();
    }

    private String fixFilename(String filename) {
        return UUID.randomUUID()
            .toString()
            .concat("-")
            .concat(filename.replace(" ", "")
                .replace(":", "")
                .replace("\\", "")
        );
    }

    private record NewApp(@NotBlank @Size(max = 100) String name) {}
    private record NewUpdate(@Version String version, @NotBlank @Size(max = 300) String overview) {}
    // TODO implement put resource
    // TODO implement upload file update    
}
