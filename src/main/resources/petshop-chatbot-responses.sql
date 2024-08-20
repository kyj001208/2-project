CREATE TABLE events (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        image_url VARCHAR(255),
                        detail_url VARCHAR(255),
                        start_date DATE,
                        end_date DATE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO events (title, description, image_url, detail_url, start_date, end_date) VALUES
                                                                                         ('봄맞이 대세일', '모든 봄철 반려동물 용품 20% 할인!', '/images/events/spring-sale.jpg', '/event/spring-sale', '2024-03-01', '2024-03-31'),
                                                                                         ('신규 회원 특별 혜택', '가입 즉시 5,000원 할인 쿠폰 지급!', '/images/events/new-member.jpg', '/event/new-member', '2024-01-01', '2024-12-31'),
                                                                                         ('건강한 먹거리 기획전', '프리미엄 사료 & 간식 최대 30% 할인', '/images/events/healthy-food.jpg', '/event/healthy-food', '2024-04-01', '2024-04-30');