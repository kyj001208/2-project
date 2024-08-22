package com.green.petfirst.service.qna.impl;

import com.green.petfirst.domain.dto.cs.qna.QnaDetailDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaListDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaSaveDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaUpdateDTO;
import com.green.petfirst.domain.entity.cs.QnaEntity;
import com.green.petfirst.domain.repository.QnaRepository;
import com.green.petfirst.service.qna.QnaService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QnaServiceProcess implements QnaService {

    private final QnaRepository qnaRepository;

	@Override
	public void deleteQna(long qnaNo) {
		qnaRepository.delete(qnaRepository.findById(qnaNo).orElseThrow());
		
	}

	@Override
	public void saveQna(QnaSaveDTO dto) {
		qnaRepository.save(dto.toEntity());
		
	}

	@Override
	@Transactional
	public void updateProcess(long qnaNo, QnaUpdateDTO dto) {
		qnaRepository.findById(qnaNo).orElseThrow().update(dto);		
	}

	@Override
	public void detailProcess(long qnaNo, Model model) {
		QnaEntity qna = qnaRepository.findById(qnaNo)
				.orElseThrow(() -> new RuntimeException("Notice not found with id: " + qnaNo));
		model.addAttribute("detail",qna.toDetailDTO());
	}

	@Override
	public void ListQna(Model model) {
		List<QnaListDTO> qnaList = qnaRepository.findAll().stream()
		.map(QnaEntity::toQnaListDTO)
		.collect(Collectors.toList());
		model.addAttribute("list",qnaList);
	}

    
}
