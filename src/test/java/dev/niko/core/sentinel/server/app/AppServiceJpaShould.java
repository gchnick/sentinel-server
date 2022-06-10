package dev.niko.core.sentinel.server.app;

import static dev.niko.core.sentinel.server.app.AppMother.getApp001;
import static dev.niko.core.sentinel.server.app.AppMother.getApp002;
import static dev.niko.core.sentinel.server.app.AppMother.getAppDTO001;
import static dev.niko.core.sentinel.server.app.AppMother.getUpdateAppDTO001;
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

import dev.niko.core.sentinel.server.exception.BadRequestException;
import dev.niko.core.sentinel.server.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
public class AppServiceJpaShould {

    @Mock
    AppJpaRepo appRepo;

    @InjectMocks
    AppServiceJpa appService;

    @Test
    void throw_exception_when_app_name_is_already_registered() {
        // Arrange
        App mockApp001 = getApp001();
        AppDTO mockNewApp = getAppDTO001();
        String appName = mockNewApp.name();

        // Act
        when(appRepo.findByNameIgnoreCase(appName)).thenReturn(Optional.of(mockApp001));

        //Assert
        assertThrows(BadRequestException.class, ()-> appService.create(appName));
    }

    @Test
    void throw_exception_when_app_by_id_not_exist() {
        // Arrange 
        String mockUUID = UUID.randomUUID().toString();

        // Assert
        assertThrows(NotFoundException.class, () -> appService.get(mockUUID));
    }

    @Test
    void throw_exception_when_app_is_updated_with_name_registered() {
        // Arrange
        App mockApp001 = getApp001();
        String idApp001 = mockApp001.getId().toString();
        
        App mockApp002 = getApp002();

        AppDTO updateApp = getUpdateAppDTO001();
        String mockAppNameRegistred = updateApp.name();
        
        // Act
        when(appRepo.findById(idApp001)).thenReturn(Optional.of(mockApp001));
        when(appRepo.findByNameIgnoreCase(mockAppNameRegistred)).thenReturn(Optional.of(mockApp002));
        
        // Assert
        assertThrows(BadRequestException.class, ()-> appService.setName(idApp001, mockAppNameRegistred));
    }
}
