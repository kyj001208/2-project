package com.green.petfirst.domain.entity.cs;

//@RequiredArgsConstructor
public enum FaqDivision {
	
	//상수필드
	MEMBER_SERVICE("회원서비스"),//0
	ORDER_PAYMENT("주문/결제"),//1
	DELIVERY("배송"),//2
	CANCEL_RETURN_EXCHANGE("취소/반품/교환"),//3
	BENEFIT("혜택"),//4
	COUPON("할인쿠폰");//5
	
	
	private final String koName;
	public String koName() {
		return koName;
	}
	
	//ordinal 은 0부터 제공하기에 편하게 1부터 사용하기 위해 만들어봤어요
	public final int koOrdinal=ordinal()+1; //1,2,3~
	public int koOrdinal() {
		return koOrdinal;
	}
	
	//*
	// String koName 필드를 초기화하는 생성자
	// @RequiredArgsConstructor 로 대신 생성함
	
	private FaqDivision(String koName) {
		this.koName=koName;
		//koOrdinal=ordinal()+1;
	}
	//*/
}
//Enum
