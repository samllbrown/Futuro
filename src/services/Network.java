package services;

import java.net.*;
import java.util.*;
import java.io.InputStream;
import java.io.IOException;

/**
 * Network.java
 * @author David Terence-Abanulo, Sam R
 * @version 1.5
 * Last Mod Date: 27/11/2021
 */
public class Network {

    /**
     * Connect to a URL.
     *
     * @param url the url
     * @return the http URL connection
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws MalformedURLException the malformed URL exception
     */
    private static HttpURLConnection connectTo(String url) throws IOException, MalformedURLException {
	return (HttpURLConnection) (new URL(url)).openConnection();
    }

    /**
     * Gets the request.
     *
     * @param url - the url
     * @return String - the request
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String getRequest(String url) throws IOException {
	HttpURLConnection connection = connectTo(url);
	connection.setRequestMethod("GET");

	Scanner scanner = new Scanner(connection.getInputStream()).useDelimiter("\\A");
	return (scanner.hasNext() ? scanner.next() : "");
    }
}
