package OLD.STUFF.board.services;
import java.net.*;
import java.util.*;
import java.io.IOException;

public class Network {
	private static HttpURLConnection connectTo(String url) throws IOException, MalformedURLException {
		return (HttpURLConnection) (new URL(url)).openConnection();
	}
	
	public static String getRequest(String url) throws IOException {
		HttpURLConnection connection = connectTo(url);
		connection.setRequestMethod("GET");

		Scanner scanner = new Scanner(connection.getInputStream()).useDelimiter("\\A");
		return (scanner.hasNext() ? scanner.next() : "");
	}
}
