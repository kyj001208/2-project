package com.green.petfirst.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.hibernate.annotations.DynamicUpdate;

import com.green.petfirst.naver.dto.NaverJogicmemDTO;
import com.green.petfirst.security.Role;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jogicdo")
@Entity
//JogicdoEntity.java
public class JogicdoEntity {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long jogicdoNo;

 private String jogicdoName;
 private String jogicdoTeam;
 private String jogicdoPosition;
 private String jogicdoNum;

 
 public NaverJogicmemDTO toListDTO() {
     return NaverJogicmemDTO.builder()
             .jogicdoName(jogicdoName)
             .jogicdoTeam(jogicdoTeam)
             .jogicdoPosition(jogicdoPosition)
             .jogicdoNum(jogicdoNum)
             .build();
 }
}
