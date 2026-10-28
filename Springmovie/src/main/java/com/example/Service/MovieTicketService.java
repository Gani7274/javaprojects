package com.example.Service;

import com.example.Ticket.MovieTicket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieTicketService {

    private final List<MovieTicket> ticketList = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Book a ticket
    public MovieTicket bookTicket(MovieTicket ticket) {
        ticket.setTicketId(idGenerator.getAndIncrement());
        ticketList.add(ticket);
        return ticket;
    }

    // Get all tickets
    public List<MovieTicket> getAllTickets() {
        return ticketList;
    }

    // Get single ticket by ID
    public MovieTicket getTicketById(Long id) {
        return ticketList.stream()
                .filter(t -> t.getTicketId() != null && t.getTicketId().equals(id))
                .findFirst()
                .orElse(null);
    }
}