package com.green.petfirst.service.notice;

import com.green.petfirst.domain.dto.cs.notice.NoticeListDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeSaveDTO;
import com.green.petfirst.domain.dto.cs.notice.NoticeUpdateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface NoticeService {
    
	
	
   //공지사항의 정보를 저장하는 메서드 (신규 등록 및 수정 시 사용 가능)
    void saveNotice(NoticeSaveDTO dto);

    
   //관리자가 공지사항 목록을 조회하고 이를 모델에 추가하는 메서드
	void adminListNotice(Model model);

    
    //특정 공지사항의 상세 정보를 조회하고 이를 모델에 추가하는 메서드
	void detailProcess(long no, Model model);

    
    //특정 공지사항을 삭제하는 메서드
	void deleteNotice(long no);

    
    //특정 공지사항을 수정하는 메서드
	void updateProcess(long no, NoticeUpdateDTO dto);
}
