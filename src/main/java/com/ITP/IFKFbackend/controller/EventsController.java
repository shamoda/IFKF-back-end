package com.ITP.IFKFbackend.controller;

import com.ITP.IFKFbackend.model.Events;
import com.ITP.IFKFbackend.repository.EventsRepository;
import com.ITP.IFKFbackend.service.EventsReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventController")
@CrossOrigin(origins = "http://localhost:3000")
public class EventsController {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private EventsReportService eventsReportService;


    //retrieve all events
    @GetMapping("/events/list")
    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    //retrieve specific event id
    @GetMapping("/events/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable Long eventId) {
        Optional<Events> events = eventsRepository.findById(eventId);
        return events.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    //delete an event
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventsRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    //update an event
    @PutMapping("/events/update")
    public ResponseEntity<Events> updateEvent(@RequestBody Events event) {
        Events updateEvent = eventsRepository.save(event);
        return new ResponseEntity<Events>(event, HttpStatus.OK);
    }

    //add an event
    @PostMapping("/events")
    public ResponseEntity<Void> createEvent(@RequestBody Events event) {
        Events addEvent = eventsRepository.save(event);

        //location
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addEvent.getEventId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //search an event by name
    @GetMapping("/events/search/{eventName}")
    public List<Events> searchEvent(@PathVariable String eventName) {
        return eventsRepository.findEventsByEventName(eventName);
    }

    //filter by event type
    @GetMapping("/events/type/{eventType}")
    public  List<Events> getEventByType(@PathVariable String eventType) {
        return eventsRepository.findByEventType(eventType);
    }

    //filter by event month
    @GetMapping("/events/month/{eventMonth}")
    public  List<Events> getEventByMonth(@PathVariable String eventMonth) {
        return eventsRepository.findByEventMonth(eventMonth);
    }

    //filter by event progress
    @GetMapping("/events/status/{eventStatus}")
    public List<Events> getEventsByProgress(@PathVariable String eventStatus) {
        boolean eStatus = false;

        if (eventStatus.equals("0"))
            eStatus = false;
        else if (eventStatus.equals("1"))
            eStatus = true;


        return eventsRepository.findByEventStatus(eStatus);
    }

    //generate report
    @GetMapping("events/report")
    public String generateEventReport() throws FileNotFoundException, JRException {
        return  eventsReportService.getEventReport();
    }

}
