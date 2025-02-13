package ru.andropol1.api;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Issue {
	private UUID id;
	private LocalDateTime issuedAt;
	private Book book;
	private Reader reader;
}
