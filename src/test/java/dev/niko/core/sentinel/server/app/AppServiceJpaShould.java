package dev.niko.core.sentinel.server.app;

import static dev.niko.core.sentinel.server.app.AppMother.getAppMap001;
import static dev.niko.core.sentinel.server.app.AppMother.getAppMap002;
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

import dev.niko.core.sentinel.server.app.domain.AppServiceImp;
import dev.niko.core.sentinel.server.app.domain.exception.BadRequestException;
import dev.niko.core.sentinel.server.app.domain.exception.NotFoundException;
import dev.niko.core.sentinel.server.app.infrastructure.mappings.AppMap;
import dev.niko.core.sentinel.server.app.infrastructure.repositories.SpringDataAppRepo;

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
        AppMap mockAppMap001 = getAppMap001();
        String appName = "Builder Tool";

        // Act
        when(appRepo.findByNameIgnoreCase(appName)).thenReturn(Optional.of(mockAppMap001));

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
        AppMap mockApp001 = getAppMap001();
        UUID idApp001 = mockApp001.getUid();
        
        AppMap mockApp002 = getAppMap002();

        String mockAppNameRegistred = "Coffe Delivery";
        
        // Act
        when(appRepo.findByUid(idApp001.toString())).thenReturn(Optional.of(mockApp001));
        when(appRepo.findByNameIgnoreCase(mockAppNameRegistred)).thenReturn(Optional.of(mockApp002));
        
        // Assert
        assertThrows(BadRequestException.class, ()-> appService.setName(idApp001, mockAppNameRegistred));
    }
}
