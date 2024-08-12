package com.green.petfirst.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    EMPLOYEE("사원")
    ,ASSISTANT_MANAGER("대리")
    ,MANAGER("과장")
    ,DEPUTY_GENERAL_MANAGER("차장")
    ,GENERAL_MANAGER("부장")
    ,TEAM_LEADER("팀장")
    ;

    private final String roleName;

    public String roleName() {
        return roleName;
    }
}

