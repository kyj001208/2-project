package com.green.petfirst.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
	PENDING("주문대기"), // 대기중
	CONFIRMED("주문확인"), // 확인됨
	PROCESSING("주문처리"), // 처리중
	DELIVERED("배송완료"), // 배송 완료
	EXCHANGE("교환"),
	REFUND("환불")
    ;

    private final String roleName;

    public String roleName() {
        return roleName;
    }
}