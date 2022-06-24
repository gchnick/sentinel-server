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
import dev.niko.core.sentinel.server.app.domain.Update;
import dev.niko.core.sentinel.server.app.domain.exception.ConflictException;
import dev.niko.core.sentinel.server.app.shared.mapper.AppMapper;
import dev.niko.core.sentinel.server.app.shared.mapper.update.UpdateMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/apps")
@RequiredArgsConstructor
public class AppResource {

    private static final String ERROR_UPLOADS = "Internal server error when uploading file. ";
    
    private final AppService appService;
    private final AppMapper appMapper;
    private final UpdateMapper updateMapper;

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
            .message("Application created successfully")
            .status(CREATED)
            .statusCode(CREATED.value())
            .build()
        );
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> get(@PathVariable UUID uid) {
        App app = appService.get(uid);
        return ResponseEntity.ok(
            appMapper.toReponse(app)
        );
    }

    @PostMapping("/{uid}/name")
    public ResponseEntity<Response> edit(@PathVariable UUID uid, @RequestBody @Valid NewApp app) {
        appService.setName(uid, app.name());
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(now())
            .message("Application name changed successfully")
            .status(OK)
            .statusCode(OK.value())
            .build()
        );
    }

    @PostMapping("/{uid}/release")
    public ResponseEntity<Response> releaseUpdate(@PathVariable UUID uid, @Valid UpdateRequest update, @RequestParam(required = true) MultipartFile file) {
        App app = appService.get(uid);
        String appName = fixString(app.getName());
        String filename = randomFilename(file.getOriginalFilename());
        String updateURL = new StringBuilder()
            .append(appName)
            .append("/")
            .append(filename)
            .toString();

        String folderPath = new StringBuilder()
            .append(path)
            .append(appName)
            .append("/")
            .toString();

        String filePath = folderPath.concat(filename);
        
        try {
            new File(folderPath).mkdir();
            file.transferTo(new File(filePath));
            app.setUpdateURL(updateURL);
        } catch (Exception e) {
            throw new ConflictException(ERROR_UPLOADS.concat(e.getMessage()));
        }

        Update request = updateMapper.toDomain(update);
        
        appService.releaseUpdate(app, request);
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(now())
            .message("New update released successfully")
            .status(OK)
            .statusCode(OK.value())
            .build()
        );
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable UUID uid) {
        appService.delete(uid);
        return ResponseEntity.noContent().build();
    }

    private String randomFilename(String filename) {
        return UUID.randomUUID()
            .toString()
            .concat("-")
            .concat(fixString(filename));
    }

    private String fixString(String str) {
        return str.replace(" ", "")
            .replace(":", "")
            .replace("\\", "");
    }

    private record NewApp(@NotBlank @Size(max = 100) String name) {}
}
