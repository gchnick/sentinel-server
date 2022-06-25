package dev.niko.core.sentinel.server.app.domain.shared;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    
    T value();
}
