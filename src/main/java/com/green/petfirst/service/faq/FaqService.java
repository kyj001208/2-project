package com.green.petfirst.service.faq;

import org.springframework.ui.Model;


import com.green.petfirst.domain.dto.cs.faq.FaqSaveDTO;
import com.green.petfirst.domain.dto.cs.faq.FaqUpdateDTO;

public interface FaqService {
	
	
	void saveFaq(FaqSaveDTO dto);
	
	void updateProcess(long faqNo, FaqUpdateDTO dto);

	void deleteFaq(long faqno);

	void faqListPrecess(Model model, int division);
}
