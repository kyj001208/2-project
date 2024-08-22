package com.green.petfirst.controller.cs;

import com.green.petfirst.domain.dto.cs.qna.QnaDetailDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaListDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaSaveDTO;
import com.green.petfirst.domain.dto.cs.qna.QnaUpdateDTO;
import com.green.petfirst.service.qna.QnaService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Q&A 관리 기능을 담당하는 컨트롤러 클래스입니다.
 * 이 클래스는 Q&A 목록 조회, 등록, 수정, 삭제 및 상세 조회를 처리합니다.
 */
@Controller
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

   //목록
    @GetMapping("/admin/customer/qna")
    public String publicList(Model model) {
        return "views/customer/qna/list"; // Q&A 목록 페이지로 이동한다
    }

    //등록페이지 
    @GetMapping("/admin/customer/qna/new")
    public String newQna() {
        return "views/customer/qna/write"; // Q&A 등록 페이지로 이동한다
    }

    //등록 후 목록으로
    @PostMapping("/admin/customer/qna")
    public String Save(QnaSaveDTO dto) {
        qnaService.saveQna(dto); // Q&A 정보를 저장한다
        return "redirect:/admin/customer/qna"; // Q&A 목록 페이지로 리디렉션한다
    }

    //상세페이지
    @GetMapping("/admin/customer/qna/{qnaNo}")
    public String detail(@PathVariable("qnaNo") long qnaNo, Model model) {
        qnaService.detailProcess(qnaNo, model);
        return "views/customer/qna/detail"; // Q&A 상세 페이지로 이동한다
    }

    //수정
    @PutMapping("/admin/customer/qna/{qnaNo}")
    public String update(@PathVariable("qnaNo") long qnaNo, QnaUpdateDTO dto) {
        qnaService.updateProcess(qnaNo, dto); // 수정된 Q&A 정보를 저장한다
        return "redirect:/admin/customer/qna//{qnaNo}"; // Q&A 목록 페이지로 리디렉션한다
    }

    //삭제
    @DeleteMapping("/admin/customer/qna/{qnaNo}")
    public String delete(@PathVariable("qnaNo") long qnaNo) {
        qnaService.deleteQna(qnaNo); // Q&A를 삭제한다
        return "redirect:/admin/customer/qna"; // Q&A 목록 페이지로 리디렉션한다
    }
}
