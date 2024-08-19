package com.green.petfirst.service.cart;

import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.pay.CartSaveDTO;
import com.green.petfirst.domain.dto.pay.CartUpdateDTO;
import com.green.petfirst.security.PetfirUserDetails;

import jakarta.servlet.http.HttpServletRequest;

public interface CartSerive {



	void deleteProcess(long no);

	void saveProcess(CartSaveDTO dto, String email);

	void listProcess(PetfirUserDetails user, Model model);

	void updateProcess(long no, CartUpdateDTO dto);

	

	

	

}
