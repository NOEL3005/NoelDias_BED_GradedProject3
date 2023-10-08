package com.example.ticketTracker.Service;

import java.util.List;

import com.example.ticketTracker.dto.Ticketdto;

public interface TicketService {

	List<Ticketdto> findAllTickets();

	void createTicket(Ticketdto ticketdto);

	Ticketdto findTickerById(Long ticketId);

	void updateTicket(Ticketdto ticketdto);

	void deleteTicket(Long ticketId);

	Ticketdto findTicketByUrl(String ticketUrl);

	List<Ticketdto> searchTickets(String query);

}
