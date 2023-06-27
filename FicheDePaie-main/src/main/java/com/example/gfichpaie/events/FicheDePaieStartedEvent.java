package com.example.gfichpaie.events;

import com.example.gfichpaie.enums.FicheDePaieStatus;
import lombok.Getter;


public class FicheDePaieStartedEvent extends BaseEvent <String> {
    @Getter private final FicheDePaieStatus status;
    public FicheDePaieStartedEvent(String id, FicheDePaieStatus status) {
        super(id);
        this.status = status;
    }



}
