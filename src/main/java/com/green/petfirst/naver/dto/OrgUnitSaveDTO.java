package com.green.petfirst.naver.dto;

import lombok.Data;
import lombok.Setter;


@Data
public class OrgUnitSaveDTO {
	
	private String orgUnitName;
	private int displayOrder;
	private int domainId;

}
