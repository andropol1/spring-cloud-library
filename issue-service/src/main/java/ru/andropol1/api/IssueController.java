package ru.andropol1.api;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.andropol1.ServiceProvider;

import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/api/issue")
public class IssueController {
	private final List<Issue> issueList;
	private final Faker faker;

	public IssueController(ServiceProvider serviceProvider) {
		this.faker = new Faker();
		final List<Issue> issueList = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			Issue issue = new Issue();
			issue.setId(UUID.randomUUID());
			Date between = faker.date().between(startOfYear(), endOfYear());
			issue.setIssuedAt(between.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			issue.setBook(serviceProvider.getRandomBook());
			issue.setReader(serviceProvider.getRandomReader());
			issueList.add(issue);
		}
		this.issueList = List.copyOf(issueList);
	}
	private Date startOfYear(){
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.YEAR, 2024);
		instance.set(Calendar.MONTH, Calendar.JANUARY);
		instance.set(Calendar.DAY_OF_MONTH, 1);
		return instance.getTime();
	}
	private Date endOfYear(){
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.YEAR, 2024);
		instance.set(Calendar.MONTH, Calendar.DECEMBER);
		instance.set(Calendar.DAY_OF_MONTH, 1);
		return instance.getTime();
	}
	@GetMapping
	public List<Issue> getAll(){
		return issueList;
	}
}
