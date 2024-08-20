package com.green.petfirst.service.event;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    public List<String> getAllEvents() {
        // 실제로는 데이터베이스나 외부 API에서 데이터를 가져오겠지만
        // 예제를 위해 간단한 리스트를 반환합니다.
        return List.of("이벤트 1", "이벤트 2", "이벤트 3");
    }
}