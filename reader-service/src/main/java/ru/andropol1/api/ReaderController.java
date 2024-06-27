package ru.andropol1.api;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/reader")
public class ReaderController {
	private final List<Reader> readerList;
	private final Faker faker;

	public ReaderController() {
		this.faker = new Faker();
		final List<Reader> readerList = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			Reader reader = new Reader();
			reader.setId(UUID.randomUUID());
			reader.setFirstName(faker.name().firstName());
			reader.setLastName(faker.name().lastName());
			readerList.add(reader);
		}
		this.readerList = List.copyOf(readerList);
	}
	@GetMapping
	public List<Reader> getAll(){
		return readerList;
	}
	@GetMapping("/random")
	public Reader getRandom(){
		return readerList.get(faker.number().numberBetween(0, readerList.size()));
	}
}
