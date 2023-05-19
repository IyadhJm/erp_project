package com.example.Erp.commonApi.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BaseEvent<T> {
    @Getter
    private  T id;
}

