package com.example.gfichpaie.events;

import lombok.Getter;

public class FDPDeletedEvent {
    @Getter
    private String ficheId;

    public FDPDeletedEvent(String ficheId) {
        this.ficheId = ficheId;
    }
}
