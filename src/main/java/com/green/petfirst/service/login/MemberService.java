package com.green.petfirst.service.login;

import com.green.petfirst.domain.dto.login.MemberDTO;
import com.green.petfirst.domain.entity.MemberEntity;

public interface MemberService {

	void save(MemberDTO dto);

	void saveSocial(MemberEntity entity);

	MemberDTO finById(Long id);

}
