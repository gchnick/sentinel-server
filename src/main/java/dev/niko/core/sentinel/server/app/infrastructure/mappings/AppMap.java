package dev.niko.core.sentinel.server.app.infrastructure.mappings;

import javax.persistence.Entity;
import javax.persistence.Table;

import dev.niko.core.sentinel.server.app.domain.App;


@Entity
@Table(name = "apps")
public class AppMap extends App {


    
}
