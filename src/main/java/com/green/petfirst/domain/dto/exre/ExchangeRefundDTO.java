package com.green.petfirst.domain.dto.exre;

import java.time.LocalDate;

import com.green.petfirst.domain.entity.RequestStatus;
import com.green.petfirst.domain.entity.RequestType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 추가
public class ExchangeRefundDTO {
    
    private long requestNo;
    private String reason;
    private LocalDate requestDate;
    private RequestStatus requestStatus;
    private RequestType requestType;
    private long orderNo;
    private long memNo;
    
    
    
}

