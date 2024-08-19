package com.green.petfirst.domain.dto.pay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CartSaveDTO {
	
	private long productNo;
	private int count;
}