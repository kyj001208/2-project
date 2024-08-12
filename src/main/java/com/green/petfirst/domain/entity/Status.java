package com.green.petfirst.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
	PENDING("대기중"), // 대기중
	CONFIRMED("확인됨"), // 확인됨
	PROCESSING("처리중"), // 처리중
	DELIVERED("배송완료") // 배송 완료
    ;

    private final String roleName;

    public String roleName() {
        return roleName;
    }
}

