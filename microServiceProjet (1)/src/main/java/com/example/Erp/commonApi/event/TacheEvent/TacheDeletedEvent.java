package com.example.Erp.commonApi.event.TacheEvent;

import com.example.Erp.commonApi.enums.tacheStatus;
import com.example.Erp.commonApi.event.BaseEvent;

public class TacheDeletedEvent extends BaseEvent<String> {
    private final tacheStatus status;
    public TacheDeletedEvent(String id) {
        super(id);
        this.status = tacheStatus.DELETED;

    }

    @Override
    public String getId() {
        return super.getId();
    }

    public tacheStatus getStatus() {
        return status;
    }
}
