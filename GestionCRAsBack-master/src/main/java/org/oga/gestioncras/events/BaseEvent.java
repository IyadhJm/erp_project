package org.oga.gestioncras.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseEvent <T> {
@Getter private T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
