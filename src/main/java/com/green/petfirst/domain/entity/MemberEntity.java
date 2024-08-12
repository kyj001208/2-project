package com.green.petfirst.domain.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;


import com.green.petfirst.security.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Entity
public class MemberEntity {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memNo;
	
	@Column(unique = true)
	private String userId; //아이디
	
	@Column(nullable = false)
	private String password; //비빌번호
	
	
	private String name; //이름
	
	
	private String adress; //주소
	
	
	private String phone;
	
	
	private String email;
	
	
	private String petName;
	
	private String petBreed;
	
	
	
	
	///////////////////////////////////////////////Role 권한 관련 ////////////////////////////////////////////////////////////////////////
	
	@Enumerated(EnumType.STRING)
	@CollectionTable( 
			name = "role",
			joinColumns = @JoinColumn(name="member_no"))
	@ElementCollection(fetch = FetchType.EAGER) 
	@Builder.Default 
	@Column(name = "role_name") 
	private Set<Role> roles=new HashSet<>(); 

	
	
	
	//Role 등록하기 위한 편의메서드
	public MemberEntity addRole(Role role) {
		roles.add(role);
		return this;
	}
	

	public MemberEntity addRoleByRange(String role) {
		
		for(int i=0; i<=Role.valueOf(role).ordinal(); i++) {
			roles.add(Role.values()[i]);
		}
		
	
		
		
		return this;
	}



}
