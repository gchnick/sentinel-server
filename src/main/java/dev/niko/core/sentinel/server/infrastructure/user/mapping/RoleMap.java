package dev.niko.core.sentinel.server.infrastructure.user.mapping;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import dev.niko.core.sentinel.server.infrastructure.shared.BaseMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleMap extends BaseMap {

    @Enumerated(STRING)
    @Column(nullable = false, length = 16)
    private UserRole role;

    protected enum UserRole {
        ROLE_ADMIN,
        ROLE_DEV,
        ROLE_CLIENT
    }
}
