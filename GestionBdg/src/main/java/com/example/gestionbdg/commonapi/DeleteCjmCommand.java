package com.example.gestionbdg.commonapi;

import lombok.Getter;

public class DeleteFDPCommand {
    @Getter
    private String ficheId;

    public DeleteFDPCommand(String ficheId) {
        this.ficheId = ficheId;
    }
}
