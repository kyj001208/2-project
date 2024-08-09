package com.green.petfirst.domain.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId; // 동물의 고유 ID

    @Column(nullable = false)
    private String name; // 동물의 이름

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetType type; // 동물의 종류 (예: 개, 고양이 등)

    @Column(nullable = false)
    private String breed; // 품종

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender; // 성별 (예: 남성, 여성)

 
    @Temporal(TemporalType.DATE)
    @Column
    private Date dateOfBirth; // 생일

}

enum PetType {
    DOG, CAT, BIRD, REPTILE, OTHER
}

enum Gender {
    MALE, FEMALE
}
