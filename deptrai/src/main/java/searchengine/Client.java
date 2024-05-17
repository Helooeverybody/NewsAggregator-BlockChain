package searchengine;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Client {
	private final String BASE_URL = "http://127.0.0.1:8000/polls/";
	private final HttpClient client;
	public Client() {
		client = HttpClient.newHttpClient();
	}

	public List<Integer> search(String keyword) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);

		String body = "?keyword=" + encodedKeyword + "&order=search";
		System.out.println(body);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL + body))
				.GET()
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// Parse the JSON response to a List of Integers
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Integer>>(){}.getType();
		List<Integer> list = gson.fromJson(response.body(), listType);
		return list;
	}
	public String trend(List<String> trendsList) throws IOException, InterruptedException{
		String trendWord = String.join("--", trendsList);
		String encodedTrendWord = URLEncoder.encode(trendWord, StandardCharsets.UTF_8);
		String body = "?trendword=" + encodedTrendWord + "&order=trend";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL + body))
				.GET()
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	
}
