package com.green.petfirst.service.cart;

import com.green.petfirst.domain.dto.pay.PaySaveDTO;

public interface PayService {

	void saveProcess(PaySaveDTO dto, String email);

}
