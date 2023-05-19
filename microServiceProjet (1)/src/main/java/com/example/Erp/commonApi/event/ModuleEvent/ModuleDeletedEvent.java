package com.example.Erp.commonApi.event.ModuleEvent;

import com.example.Erp.commonApi.enums.moduleStatus;
import com.example.Erp.commonApi.event.BaseEvent;

public class ModuleDeletedEvent extends BaseEvent<String> {
    private final moduleStatus status;
    public ModuleDeletedEvent(String id) {
        super(id);
        this.status = moduleStatus.DELETED;

    }

    @Override
    public String getId() {
        return super.getId();
    }

    public moduleStatus getStatus() {
        return status;
    }
}
