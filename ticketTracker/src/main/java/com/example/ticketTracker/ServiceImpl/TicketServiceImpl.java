package com.example.ticketTracker.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ticketTracker.dto.Ticketdto;
import com.example.ticketTracker.entity.Ticket;
import com.example.ticketTracker.mapper.TicketMapper;
import com.example.ticketTracker.repository.TicketRepository;
import com.example.ticketTracker.Service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	// @Autowired not required for one constructor from spring 3 onwards
	private TicketRepository ticketRepository;

	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<Ticketdto> findAllTickets() {

		List<Ticket> tickets = ticketRepository.findAll();

		// return tickets.stream().map((ticket) ->
		// TicketMapper.mapToTicketDto(ticket)).collect(Collectors.toList());

		return tickets.stream().map(TicketMapper::mapToTicketdto).collect(Collectors.toList());
	}

	@Override
	public void createTicket(Ticketdto ticketdto) {

		Ticket ticket = TicketMapper.mapToTicket(ticketdto);
		ticketRepository.save(ticket);

	}

	@Override
	public Ticketdto findTickerById(Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId).get();
		return TicketMapper.mapToTicketdto(ticket);
	}

	@Override
	public void updateTicket(Ticketdto ticketdto) {
		Ticket ticket = TicketMapper.mapToTicket(ticketdto);
		ticketRepository.save(ticket);

	}

	@Override
	public void deleteTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);

	}

	@Override
	public Ticketdto findTicketByUrl(String ticketUrl) {
		Ticket ticket = ticketRepository.findByUrl(ticketUrl).get();
		return TicketMapper.mapToTicketdto(ticket);
	}

	@Override
	public List<Ticketdto> searchTickets(String query) {
		List<Ticket> tickets = ticketRepository.searchTickets(query);
		return tickets.stream().map(TicketMapper::mapToTicketdto).collect(Collectors.toList());
	}

	

	

}
