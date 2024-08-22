package com.green.petfirst.naver.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NaverJogicmemDTO {
	
	private long jogicdoNo; //조직도번호
	private String jogicdoName;// 조직도이름
	private String jogicdoTeam; //조직도 팀명
	private String jogicdoPosition; //조직도 직급
	private String jogicdoNum; //조직도 전화번호
	

}
