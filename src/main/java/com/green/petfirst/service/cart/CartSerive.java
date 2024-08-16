package com.green.petfirst.service.cart;

import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.pay.CartSaveDTO;

public interface CartSerive {

	void saveProcess(CartSaveDTO dto);

	void listProcess(Model model);

}
