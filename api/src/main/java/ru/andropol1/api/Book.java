package ru.andropol1.api;

import lombok.Data;

import java.util.UUID;

@Data
public class Book {
	private UUID ID;
	private String name;
	private Author author;
}
