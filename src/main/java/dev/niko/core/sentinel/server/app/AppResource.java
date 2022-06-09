package dev.niko.core.sentinel.server.app;

import static java.util.Map.of;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
// import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.niko.core.sentinel.server.util.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/apps")
@RequiredArgsConstructor
public class AppResource {

    private final AppService appService;

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody @Valid AppDTO app) {
        App createdApp = appService.create(app);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdApp.getId().toString())
            .toUri();
        return ResponseEntity.created(uri).body(
            Response.builder()
            .timeStamp(now())
            .data(of("app", createdApp))
            .status(CREATED)
            .statusCode(CREATED.value())
            .build()
        );
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Response> get(@PathVariable String uid) {
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(now())
            .data(of("app", appService.get(uid)))
            .status(OK)
            .statusCode(OK.value())
            .build()
        );
    }    
}
