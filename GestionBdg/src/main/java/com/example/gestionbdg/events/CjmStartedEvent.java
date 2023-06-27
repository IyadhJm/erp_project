package com.example.gestionbdg.events;


import com.example.gestionbdg.enums.GbdgStatus;
import lombok.Getter;



public class CjmStartedEvent extends BaseEvent <String> {
    @Getter private final GbdgStatus status;
    public CjmStartedEvent(String id, GbdgStatus status) {
        super(id);
        this.status = status;
    }



}
