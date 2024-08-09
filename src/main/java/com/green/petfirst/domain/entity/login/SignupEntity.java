package com.green.petfirst.domain.entity.login;

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
@Table(name = "signupentity")
@Entity
public class SignupEntity {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false, unique = true)
	private String userID; //아이디
	
	@Column(nullable = false)
	private String password; //비빌번호
	
	@Column(nullable = false)
	private String name; //이름
	
	@Column(nullable = false)
	private String adress; //주소
	
	@Column(nullable = false)
	private String usernumber;
	
	@Column(nullable = false)
	private String useremail;
	
	
	private String petname;
	
	private String petbreed;
	
	
	
	
	///////////////////////////////////////////////Role 권한 관련 ////////////////////////////////////////////////////////////////////////
	
	@Enumerated(EnumType.STRING)// DB 저장 데이터타입 문자로 저장 이라는 뜻 : default 숫자로 저장
	@CollectionTable( //  접근 가능한 내장 테이블이 role이라는 뜻
			name = "role", //RoleEnum 엔티티 이름 
			joinColumns = @JoinColumn(name="member_no"))//fk 컬럼명 이름 지정 (선택사항-자동으로 생성)
	@ElementCollection(fetch = FetchType.EAGER) // 1:N  설계 , MemberEntity에서만 접근 가능한 내장테이블이라는 뜻 / EAGER:즉시로딩 , LAZY: 지열로딩
	@Builder.Default //Roles :초기화를 넣었기에 빌더로 사용 불가하기에 Builder.Default 넣어줘야 노란 경고장 사라짐 
	@Column(name = "role_name") //Role엔티티에서 컬럼명 변경, 변경하지 않을 경우 set콜렉션의 변수 이름으로 들어감 
	private Set<Role> roles=new HashSet<>(); //role엔터티(테이블)을 만들어 별도 관리(1차 정규화) 그러나 지금 Role 경우 엔터티가 아닌 Enum으로 만들어져있어 테이블이라고 지정 필요

	
	
	
	//Role 등록하기 위한 편의메서드
	public SignupEntity addRole(Role role) {
		roles.add(role);
		return this;
	}
	
	//0:emp,1:mgr, 2:admin 순차적으로 세팅이되어 있는 경우에 활용할수 있다.
	//해당하는 롤을 범위 단위로 만든 편의메서드 (중첩 방식)
	public SignupEntity addRoleByRange(String role) {
		
		for(int i=0; i<=Role.valueOf(role).ordinal(); i++) {
			roles.add(Role.values()[i]);
		}
		
		/* 2번 방법
		switch(role) {
		case "ADMIN": 
			entity.addRole(Role.ADMIN);
		
		case "MGR":
			entity.addRole(Role.MGR);
		case "EMP":
			entity.addRole(Role.EMP);
		}
		*/
		
		
		return this;
	}
	
	

}
