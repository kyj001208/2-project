package com.green.petfirst.service.admin.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.petfirst.domain.dto.deliver.DeliverDTO;
import com.green.petfirst.domain.entity.DeliverEntity;
import com.green.petfirst.domain.entity.MemberEntity;
import com.green.petfirst.domain.entity.OrderEntity;
import com.green.petfirst.domain.repository.DeliverRepository;
import com.green.petfirst.domain.repository.MemberRepository;
import com.green.petfirst.domain.repository.OrderRepository;
import com.green.petfirst.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceProcess implements AdminService {

	private final MemberRepository memberRep;
	private final DeliverRepository deliverRep;
	private final OrderRepository orderRep;

	@Override
	public void ListProcess(Model model) {
		long totalMembers = memberRep.countAllMembers(); // 총 회원 수 계산
		model.addAttribute("totalMembers", totalMembers); // 모델에 총 회원 수 추가

	}

	@Override
	public Page<DeliverDTO> getDeliverList(String devNo, String devTime, String devComplete, String devCompany,
			Pageable pageable) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate startTime = (devTime != null && !devTime.isEmpty()) ? LocalDate.parse(devTime, formatter) : null;
		LocalDate completeDate = (devComplete != null && !devComplete.isEmpty())
				? LocalDate.parse(devComplete, formatter)
				: null;

		// 모든 데이터 가져오기
		List<DeliverEntity> deliverList = deliverRep.findAll();

		// 필터링 수행
		List<DeliverDTO> filteredList = deliverList.stream().filter(
				deliver -> (devNo == null || devNo.isEmpty() || String.valueOf(deliver.getDevNo()).contains(devNo))
						&& (startTime == null || deliver.getDevTime().isEqual(startTime)) // devTime 날짜 필터링
						&& (completeDate == null || deliver.getDevComplete().isEqual(completeDate)) // devComplete 날짜
																									// 필터링
						&& (devCompany == null || devCompany.isEmpty() || deliver.getDevCompany().equals(devCompany)))
				.map(deliver -> {
					String address = memberRep.findById(deliver.getMember().getMemNo()).map(MemberEntity::getAddress)
							.orElse("주소 미등록");
					return DeliverDTO.builder().devNo(deliver.getDevNo()).devCompany(deliver.getDevCompany())
							.devTime(deliver.getDevTime()).devComplete(deliver.getDevComplete())
							.memNo(deliver.getMember().getMemNo()).address(address).build();
				}).collect(Collectors.toList());

		// 필터링된 데이터로 페이지 생성
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), filteredList.size());
		List<DeliverDTO> pageContent = filteredList.subList(start, end);

		return new PageImpl<>(pageContent, pageable, filteredList.size());
	}

	@Override
	public Map<String, Long> SalesDataProcess(LocalDate startDate, LocalDate endDate) {
		
		List<OrderEntity> orders = orderRep.findByOrderDateBetween(startDate, endDate);
        Map<String, Long> salesData = new HashMap<>();

        for (OrderEntity order : orders) {
            String orderDate = order.getOrderDate().toString();
            long total = order.getTotal();
            salesData.merge(orderDate, total, Long::sum);
        }

        return salesData;
    }

}
