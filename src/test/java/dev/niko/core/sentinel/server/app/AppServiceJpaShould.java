package dev.niko.core.sentinel.server.app;

import static dev.niko.core.sentinel.server.app.AppMother.getApp001;
import static dev.niko.core.sentinel.server.app.AppMother.getApp002;
import static dev.niko.core.sentinel.server.app.AppMother.getNewApp001;
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
        App mockNewApp = getNewApp001();
        String appName = mockNewApp.getName();

        // Act
        when(appRepo.findByNameIgnoreCase(appName)).thenReturn(Optional.of(mockApp001));

        //Assert
        assertThrows(BadRequestException.class, ()-> appService.create(mockNewApp));
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
        String mockAppName = "Coffe Delivery";
        App mockApp001 = getApp001();
        String idApp001 = mockApp001.getId().toString();
        App mockApp002 = getApp002();

        App updateApp = AppMother.getNewApp001();
        updateApp.setName(mockAppName);
 
        // Act
        when(appRepo.findById(idApp001)).thenReturn(Optional.of(mockApp001));
        when(appRepo.findByNameIgnoreCase(mockAppName)).thenReturn(Optional.of(mockApp002));
        
        // Assert
        assertThrows(BadRequestException.class, ()-> appService.update(idApp001, updateApp));
    }
}
