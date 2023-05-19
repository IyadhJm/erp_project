package com.oga.leave.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent<T> {
    @Getter private T id;


}
