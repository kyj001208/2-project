-- 챗봇 응답 샘플 데이터 삽입
INSERT INTO chatbot_responses (keywords, response_text, context_id, next_context_id) VALUES
                                                                                         ('안녕,시작,도움', '안녕하세요! 반려동물 용품 전문점 PetFirst입니다. 어떤 도움이 필요하신가요? 강아지 용품이나 고양이 용품에 대해 물어보세요.', 'greeting', 'pet_type'),
                                                                                         ('강아지,견,멍멍이', '강아지 용품을 찾으시는군요! 사료, 간식, 장난감 중 어떤 것에 관심이 있으신가요?', 'pet_type', 'dog_product_type'),
                                                                                         ('고양이,냥이,고양', '고양이 용품을 찾고 계시네요! 사료, 간식, 장난감 중 무엇을 찾으시나요?', 'pet_type', 'cat_product_type'),
                                                                                         ('사료,먹이', '사료는 반려동물의 건강에 매우 중요합니다. 어떤 종류의 사료를 찾으시나요? 연령대나 특별한 요구사항이 있다면 말씀해 주세요.', 'product_type', 'specific_product'),
                                                                                         ('간식,츄르,개껌', '간식은 반려동물과의 교감에 좋습니다. 어떤 종류의 간식을 찾으시나요? 알러지가 있다면 말씀해 주세요.', 'product_type', 'specific_product'),
                                                                                         ('장난감,놀이', '장난감은 반려동물의 스트레스 해소에 좋습니다. 어떤 유형의 장난감을 찾으시나요? 실내용 또는 실외용으로 나뉩니다.', 'product_type', 'specific_product'),
                                                                                         ('가격,얼마', '제품의 가격대는 다양합니다. 특정 제품의 가격을 알고 싶으시면 제품명을 말씀해 주세요.', 'specific_product', 'price_inquiry'),
                                                                                         ('할인,세일,특가', '현재 진행 중인 할인 상품들이 있습니다. 어떤 종류의 제품 할인에 관심이 있으신가요?', 'specific_product', 'discount_inquiry'),
                                                                                         ('배송,배달,언제', '주문 후 일반적으로 1-3일 내에 배송이 완료됩니다. 특정 주문의 배송 상태를 확인하고 싶으시면 주문번호를 알려주세요.', 'delivery', 'order_inquiry'),
                                                                                         ('감사,고마워', '저희 PetFirst를 이용해 주셔서 감사합니다. 더 필요한 것이 있으시면 언제든 문의해 주세요!', 'thanks', 'greeting');