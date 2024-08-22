package com.green.petfirst.service.qna;

import com.green.petfirst.domain.dto.cs.qna.QnaSaveDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaUpdateDTO;


import org.springframework.ui.Model;

public interface QnaService {


    void deleteQna(long qnaNo);

	void saveQna(QnaSaveDTO dto);
	
	void updateProcess(long qnaNo, QnaUpdateDTO dto);
	
	void detailProcess (long qnaNo, Model model);
	
	void ListQna(Model model);
	
	
}

