package com.example.ticketTracker.mapper;



import com.example.ticketTracker.dto.Ticketdto;
import com.example.ticketTracker.entity.Ticket;

//It will map Ticket Entity to TicketDto and vice version
public class TicketMapper {

	//map Ticket Entity to Ticket DTO
	
	public static Ticketdto mapToTicketdto(Ticket ticket) {
		return Ticketdto.builder()
				.id(ticket.getId())
				.title(ticket.getTitle())
				.url(ticket.getUrl())
				.content(ticket.getContent())
				.shortDescription(ticket.getShortDescription())
				.createdOn(ticket.getCreatedOn())
				.updatedOn(ticket.getUpdatedOn())
				.build();
	}
	
	// map TicketDto to Ticket entity
	public static Ticket mapToTicket(Ticketdto ticketdto) {
		return Ticket.builder()
				.id(ticketdto.getId())
				.title(ticketdto.getTitle())
				.content(ticketdto.getContent())
				.url(ticketdto.getUrl())
				.shortDescription(ticketdto.getShortDescription())
				.createdOn(ticketdto.getCreatedOn())
				.updatedOn(ticketdto.getUpdatedOn())
				.build();
	}
}
