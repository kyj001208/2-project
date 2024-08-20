package com.green.petfirst.service.login;

import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.domain.entity.MemberEntity;

public interface MemberService {

	void save(MemberDTO dto);

	void saveSocial(MemberEntity entity);

	void findByEmail(String email, Model model);

	void updateMember(MemberDTO dto);


}
