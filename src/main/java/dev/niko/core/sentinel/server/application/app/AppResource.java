package dev.niko.core.sentinel.server.application.app;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.Files.copy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
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

import dev.niko.core.sentinel.server.application.app.request.AppRequest;
import dev.niko.core.sentinel.server.application.app.request.UpdateRequest;
import dev.niko.core.sentinel.server.application.shared.Response;
import dev.niko.core.sentinel.server.domain.App;
import dev.niko.core.sentinel.server.domain.AppService;
import dev.niko.core.sentinel.server.domain.Update;
import dev.niko.core.sentinel.server.shared.mapper.AppMapper;
import dev.niko.core.sentinel.server.shared.mapper.update.UpdateMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/apps")
@RequiredArgsConstructor
public class AppResource {

    private static final String FILE_NOT_FOUNT = "File not found.";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_DEV = "ROLE_DEV";
    private static final String ROLE_CLIENT = "ROLE_CLIENT";
    private static final String PATH = System.getProperty("user.home") + "/sentinel/uploads/";
    
    private final AppService appService;
    private final AppMapper appMapper;
    private final UpdateMapper updateMapper;

    @Secured({ROLE_ADMIN, ROLE_DEV})
    @PostMapping
    public ResponseEntity<Response> save(@RequestBody @Valid AppRequest app) {
        App request = appMapper.toDomain(app);
        UUID uid = appService.create(request);
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

    
    @Secured(ROLE_CLIENT)
    @GetMapping("/{uid}")
    public ResponseEntity<?> get(@PathVariable UUID uid) {
        App app = appService.get(uid);
        return ResponseEntity.ok(
            appMapper.toReponse(app)
        );
    }

    /**
     * TODO Usar excepciones conocidas
     * TODO implementar actualizacion en un solo paso
     */
    @Secured(ROLE_CLIENT)
    @GetMapping("/{uid}/{version}/update")
    public ResponseEntity<?> update(@PathVariable UUID uid, @PathVariable String version) throws IOException {

        App app = appService.get(uid);
        String file = app.getUpdateURL();
        Path filePath = Paths.get(PATH, file).toAbsolutePath().normalize();

        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(FILE_NOT_FOUNT);
        }

        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", file);
        httpHeaders.add("CONTENT_DISPOSITTION", "attachment;File-Name=" + resource.getFilename());

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
            .headers(httpHeaders)
            .body(resource);
    }

    @Secured(ROLE_DEV)
    @PostMapping("/{uid}/name")
    public ResponseEntity<Response> edit(@PathVariable UUID uid, @RequestBody @Valid AppRequest app) {
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

    @Secured({ROLE_ADMIN, ROLE_DEV})
    @PostMapping("/{uid}/release")
    public ResponseEntity<Response> releaseUpdate(@PathVariable UUID uid, @Valid UpdateRequest update, @RequestParam(required = true) MultipartFile file) throws IOException {

        App app = appService.get(uid);
        String appName = StringUtils.cleanPath(app.getName());
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String randomFilename = randomFilename(filename);
        Path fileStorage = Paths.get(PATH, appName, randomFilename).toAbsolutePath().normalize();
        Path url = Paths.get(appName, randomFilename).toAbsolutePath().normalize();
        
        copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);  
        app.setUpdateURL(url.toString());

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

    @Secured(ROLE_ADMIN)
    @DeleteMapping("/{uid}")
    public ResponseEntity<?> delete(@PathVariable UUID uid) {
        appService.delete(uid);
        return ResponseEntity.noContent().build();
    }

    private String randomFilename(String filename) {
        return UUID.randomUUID()
            .toString()
            .concat("-")
            .concat(filename);
    }
}
