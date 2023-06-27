package com.example.gestionbdg.commonapi;

import lombok.Getter;

public class DeleteCjmCommand {
    @Getter
    private String bdgId;

    public DeleteCjmCommand(String bdgId) {
        this.bdgId = bdgId;
    }
}
