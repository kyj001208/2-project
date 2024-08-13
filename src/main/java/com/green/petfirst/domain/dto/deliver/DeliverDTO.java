package com.green.petfirst.domain.dto.deliver;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliverDTO {
	
	private Long devNo;
	private String devCompany;
	private LocalDate devTime;
	private LocalDate devComplete;
	private Long memNo;
	private String address; // 주소를 추가
	
}
