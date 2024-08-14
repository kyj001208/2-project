package com.green.petfirst.service.admin;

import org.springframework.ui.Model;

import com.green.petfirst.security.PetfirUserDetails;

import jakarta.servlet.http.HttpSession;

public interface AdminService {

	void ListProcess(Model model);

	void DeliverList(String devNo, String devTime, String devComplete, String devCompany, Model model);

	void exreListProcess(Model model);

}
