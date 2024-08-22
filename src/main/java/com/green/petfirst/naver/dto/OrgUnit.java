package com.green.petfirst.naver.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //속서을 모두 매핑하려고 하면 힘들기때문에 없는것은 무시하기 위해 사용
@Getter
public class OrgUnit {
	

	private int displayLevel;
	private int displayOrder;
	private String orgUnitExternalKey;
	private String orgUnitId;		//부서id
	private String orgUnitName;   //부서이름
	private String parentExternalKey;
	private String parentOrgUnitId;

	
	
}
