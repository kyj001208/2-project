package com.green.petfirst.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RequestType {
    EXCHANGE("교환")
    ,REFUND("환불")
    ;

    private final String roleName;

    public String roleName() {
        return roleName;
    }
}

