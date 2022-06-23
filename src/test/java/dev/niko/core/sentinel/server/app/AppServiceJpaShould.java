package dev.niko.core.sentinel.server.app;

import static dev.niko.core.sentinel.server.app.AppMother.getApp001;
import static dev.niko.core.sentinel.server.app.AppMother.getApp002;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.niko.core.sentinel.server.app.domain.App;
import dev.niko.core.sentinel.server.app.domain.AppServiceImp;
import dev.niko.core.sentinel.server.app.domain.exception.BadRequestException;
import dev.niko.core.sentinel.server.app.domain.exception.NotFoundException;
import dev.niko.core.sentinel.server.app.infrastructure.AppJpaRepo;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
public class AppServiceJpaShould {

    @Mock
    SpringDataAppRepo appRepo;

    @InjectMocks
    AppServiceImp appService;

    @Test
    void throw_exception_when_app_name_is_already_registered() {
        // Arrange
        App mockApp001 = getApp001();
        String appName = "Builder Tool";

        // Act
        when(appRepo.findByNameIgnoreCase(appName)).thenReturn(Optional.of(mockApp001));

        //Assert
        assertThrows(BadRequestException.class, ()-> appService.create(appName));
    }

    @Test
    void throw_exception_when_app_by_id_not_exist() {
        // Arrange 
        UUID mockUUID = UUID.randomUUID();

        // Assert
        assertThrows(NotFoundException.class, () -> appService.get(mockUUID));
    }

    @Test
    void throw_exception_when_app_is_updated_with_name_registered() {
        // Arrange
        App mockApp001 = getApp001();
        UUID idApp001 = UUID.fromString(mockApp001.getUid());
        
        App mockApp002 = getApp002();

        String mockAppNameRegistred = "Coffe Delivery";
        
        // Act
        when(appRepo.findByUid(idApp001.toString())).thenReturn(Optional.of(mockApp001));
        when(appRepo.findByNameIgnoreCase(mockAppNameRegistred)).thenReturn(Optional.of(mockApp002));
        
        // Assert
        assertThrows(BadRequestException.class, ()-> appService.setName(idApp001, mockAppNameRegistred));
    }
}
