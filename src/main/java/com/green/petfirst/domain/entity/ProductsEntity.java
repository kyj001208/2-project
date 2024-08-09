/*
 * package com.green.petfirst.domain.entity;
 * 
 * import org.hibernate.annotations.DynamicUpdate;
 * 
 * import jakarta.persistence.Column; import jakarta.persistence.Entity; import
 * jakarta.persistence.GeneratedValue; import
 * jakarta.persistence.GenerationType; import jakarta.persistence.JoinColumn;
 * import jakarta.persistence.ManyToOne; import jakarta.persistence.Id; import
 * jakarta.persistence.Table; import lombok.AllArgsConstructor; import
 * lombok.Builder; import lombok.Getter; import lombok.NoArgsConstructor;
 * 
 * @DynamicUpdate
 * 
 * @Getter
 * 
 * @Builder
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * 
 * @Entity
 * 
 * @Table(name="products") public class ProductsEntity {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long productNo;
 * //상품번호
 * 
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "categoryId", nullable = false) // 외래 키 설정 private
 * CategoryEntity categoryId; // 카테고리 엔티티
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "categoryId", nullable = false) // 외래 키 설정
 * 
 * @Column(nullable = false) private long memNo; //회원번호
 * 
 * @Column(columnDefinition = "TEXT", nullable = false) private String
 * productName; //상품명
 * 
 * @Column(nullable = false) private long price; //상품가격
 * 
 * @Column(columnDefinition = "TEXT", nullable = false) private String
 * productExplain; //상품설명
 * 
 * @Column(nullable = false) private long quantity; //상품수량
 * 
 * @Column private long discount; //즉시할인
 * 
 * @Column private long discountPrice; //즉시할인
 * 
 * 
 * }
 */
