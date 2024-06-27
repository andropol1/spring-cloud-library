package ru.andropol1;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.andropol1.api.Book;
import ru.andropol1.api.Reader;

@Service
public class ServiceProvider {
	private final WebClient webClient;

	public ServiceProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
		webClient = WebClient.builder()
				.filter(loadBalancerExchangeFilterFunction)
				.build();
	}

	public Book getRandomBook() {
		Book randomBook = webClient.get()
				.uri("http://book-service/api/book/random")
				.retrieve()
				.bodyToMono(Book.class)
				.block();
		return randomBook;
	}

	public Reader getRandomReader() {
		Reader randomReader = webClient.get()
				.uri("http://reader-service/api/reader/random")
				.retrieve()
				.bodyToMono(Reader.class)
				.block();
		return randomReader;
	}
}
