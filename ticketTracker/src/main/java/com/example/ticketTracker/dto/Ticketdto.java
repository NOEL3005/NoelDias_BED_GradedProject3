package com.example.ticketTracker.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticketdto { // act as the model in spring MVC web application

	private Long id;

	@NotEmpty
	private String title;
	private String url;
	@NotEmpty
	private String content;
	@NotEmpty
	private String shortDescription;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;

}
