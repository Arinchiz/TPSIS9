package com.autoshop.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationStatus {
    WAITING("Ожидание"),
    DONE("Подтверждено"),
    REJECT("Отказано"),
    ;
    private final String name;
}

