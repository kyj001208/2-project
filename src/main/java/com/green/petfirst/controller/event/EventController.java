package com.green.petfirst.controller.event;

import com.green.petfirst.domain.entity.Event;
import com.green.petfirst.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/public/events")
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "views/event/events";
    }

    @GetMapping("/public/event/{id}")
    public String eventDetail(@PathVariable Long id, Model model) {
        Optional<Event> eventOpt = eventService.getEventById(id);
        if (eventOpt.isPresent()) {
            model.addAttribute("event", eventOpt.get());
            return "views/event/event-detail";
        } else {
            return "redirect:/events";
        }
    }
}