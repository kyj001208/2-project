package com.green.petfirst.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RequestStatus {
	PENDING("대기중"),        // 대기중
    EXCHANGE_DONE("교환완료"),  // 교환완료
    REFUND_DONE("환불완료"),    // 환불완료
    EXCHANGE_CANCELLED("교환취소"), // 교환취소
    REFUND_CANCELLED("환불취소")    // 환불취소
    ;

    private final String roleName;

    public String roleName() {
        return roleName;
    }
}
