package com.green.petfirst.service.admin;

import org.springframework.ui.Model;

public interface AdminService {

	void ListProcess(Model model);

	void DeliverList(String devNo, String devTime, String devComplete, String devCompany, Model model);

}
