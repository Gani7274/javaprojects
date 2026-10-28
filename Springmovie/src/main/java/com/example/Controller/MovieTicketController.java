package com.example.Controller;

import com.example.Ticket.MovieTicket;
import com.example.Service.MovieTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class MovieTicketController {

    private final MovieTicketService ticketService;

    public MovieTicketController(MovieTicketService ticketService) {
        this.ticketService = ticketService;
    }

    // 1. POST /api/tickets -> Book a ticket
    @PostMapping
    public ResponseEntity<MovieTicket> bookTicket(@Validated @RequestBody MovieTicket ticket) {
        MovieTicket savedTicket = ticketService.bookTicket(ticket);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

    // 2. GET /api/tickets -> View all tickets
    @GetMapping
    public ResponseEntity<List<MovieTicket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // 3. GET /api/tickets/{id} -> View ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        MovieTicket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket not found for ID: " + id);
        }
        return ResponseEntity.ok(ticket);
    }
}