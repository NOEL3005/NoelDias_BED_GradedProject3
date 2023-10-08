package com.example.ticketTracker.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ticketTracker.dto.Ticketdto;
import com.example.ticketTracker.Service.TicketService;

@Controller
public class ticketController {

	private TicketService ticketService;

	// constructor based DI
	public ticketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/admin/tickets")
	// create handler method, GET request and return model and view
	public String tickets(Model model) {
		List<Ticketdto> tickets = ticketService.findAllTickets();
		model.addAttribute("tickets", tickets); // key value pair
		return "/admin/tickets";
	}

	// handler method to handle new Ticket request
	@GetMapping("admin/tickets/newTicket")
	public String newTicketForm(Model model) {
		Ticketdto ticketdto = new Ticketdto();
		model.addAttribute("ticket", ticketdto);
		return "admin/create_ticket";
	}

	// handler method to handle form submit request
	@PostMapping("/admin/tickets")
	public String createTicket(@Valid @ModelAttribute("ticket") Ticketdto ticketdto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ticket", ticketdto);
			return "admin/create_ticket";
		}
		ticketdto.setUrl(getUrl(ticketdto.getTitle()));
		ticketService.createTicket(ticketdto);
		return "redirect:/admin/tickets";
	}

	private static String getUrl(String ticketTitle) {

		String title = ticketTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url = url.replaceAll("[A-Za-z0-9]", "=");
		return url;
	}

	// handler method to handle edit ticket request
	@GetMapping("/admin/tickets/{ticketId}/edit")
	public String editTicketForm(@PathVariable("ticketId") Long ticketId, Model model) {

		Ticketdto ticketdto = ticketService.findTickerById(ticketId);
		model.addAttribute("ticket", ticketdto);
		return "admin/edit_ticket";

	}

	// handler method to handle edit ticket form submit request
	@PostMapping("/admin/tickets/{ticketId}")
	public String updateTicket(@PathVariable("ticketId") long ticketId,
			@Valid @ModelAttribute("ticket") Ticketdto ticket, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ticket", ticket);
			return "admin/edit_ticket";
		}
		ticket.setId(ticketId);
		ticketService.updateTicket(ticket);
		return "redirect:/admin/tickets";
	}

	// handler method to delete the ticket request
	@GetMapping("/admin/tickets/{ticketId}/delete")

	public String deleteTicket(@PathVariable("ticketId") Long ticketId) {
		ticketService.deleteTicket(ticketId);
		return "redirect:/admin/tickets";
	}

	// handler method to view Ticket request
	@GetMapping("/admin/ticket/{ticketUrl}/view")
	public String viewPost(@PathVariable("ticketUrl") String ticketUrl, Model model) {
		Ticketdto ticketdto = ticketService.findTicketByUrl(ticketUrl);
		model.addAttribute("ticket", ticketdto);
		return "admin/view_ticket";
	}

	// handler method to handle search tickets
	// localhost:8080/admin/tickets/search?query=tomcat
	@GetMapping("/admin/tickets/search")
	public String searchTickets(@RequestParam(value = "query") String query, Model model) {

		List<Ticketdto> tickets = ticketService.searchTickets(query);
		model.addAttribute("tickets", tickets);
		return "admin/tickets";

	}

}