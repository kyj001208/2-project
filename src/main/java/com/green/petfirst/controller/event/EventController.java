package com.green.petfirst.controller.event;

import com.green.petfirst.service.event.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/public/shop/events")
    public String showEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "views/event/events"; // 이벤트 뷰 템플릿
    }
}
