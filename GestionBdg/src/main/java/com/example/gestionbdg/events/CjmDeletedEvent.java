package com.example.gestionbdg.events;

import lombok.Getter;

public class CjmDeletedEvent {
    @Getter
    private String bdgId;

    public CjmDeletedEvent(String bdgId) {
        this.bdgId = bdgId;
    }
}
