package com.green.petfirst.service.faq.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.cs.faq.FaqDetailDTO;
import com.green.petfirst.domain.dto.cs.faq.FaqSaveDTO;
import com.green.petfirst.domain.dto.cs.faq.FaqUpdateDTO;
import com.green.petfirst.domain.entity.cs.FaqDivision;
import com.green.petfirst.domain.entity.cs.FaqEntity;
import com.green.petfirst.domain.repository.FaqRepository;
import com.green.petfirst.service.faq.FaqService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FaqServiceProcess implements FaqService {

    private final FaqRepository faqRepository;

    @Override
    public void saveFaq(FaqSaveDTO dto) {
    	faqRepository.save(dto.toEntity());
    	
    }
	
	@Override
	public void deleteFaq(long faqno) {
		faqRepository.delete(faqRepository.findById(faqno).orElseThrow());
	}

	@Override
	@Transactional
	public void updateProcess(long faqNo, FaqUpdateDTO dto) {
		faqRepository.findById(faqNo).orElseThrow().update(dto);
		
	}

	@Override
	public void faqListPrecess(Model model, int division) {
		List<FaqDetailDTO> faqList = faqRepository.findByDivision(FaqDivision.values()[division-1]).stream()
				.map(FaqEntity::toFaqDetailDTO)
				.collect(Collectors.toList());
		model.addAttribute("list" , faqList);
		
	}
	
	
}
