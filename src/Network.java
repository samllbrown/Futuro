import java.net.*;
import java.util.*;
import java.io.InputStream;
import java.io.IOException;

public class Network {
	
	// TODO: consider using httpclient
	// TODO: NEED TO MAKE SURE WE USE TRY/CATCH WITH ANY NETWORKING + CLOSE THE SCANNER IN THE GET FUNCTION
	
	private static final String CS_WEB_CAT_URL = "http://cswebcat.swansea.ac.uk/puzzle";
	private static final char[] ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	private static HttpURLConnection connect(String url) throws IOException, MalformedURLException {
		return (HttpURLConnection) (new URL(url)).openConnection();
	}

	// using a bytearrayoutputstream // reading it might be way better here
	private static String get(String url) throws IOException {
		HttpURLConnection connection = connect(url);
		connection.setRequestMethod("GET");

		Scanner scanner = new Scanner(connection.getInputStream()).useDelimiter("\\A");
		return (scanner.hasNext() ? scanner.next() : "");
	}
	
	private static char getShift(int direction, int fromIndex, int shift) {
		// remove magic number
		return ALPHA[(fromIndex + (direction * shift)) % 26];
        // return code[((maxIndex + (direction * shift))) % maxIndex];
	}

	private static String solvePuzzle(String puzzleCode) {
		int lengthOfCode = puzzleCode.length();
		int maxIndex = lengthOfCode-1;
		int charCountOfShift = 0;
		String solved = "";
		int dir = -1;

		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < 26; i++) {
			hm.put(String.valueOf(ALPHA[i]), Integer.valueOf(i));
		}
		
		for(int i = 0; i < lengthOfCode; i++) {
			solved += String.valueOf(getShift(dir, (int) hm.get(puzzleCode.charAt(i)), i+1));
			dir = dir * -1;
		}
		charCountOfShift = solved.length();
		// magic var - make final
		solved += "CS-230";
		// declarations should be at start...
		String solution = (String.valueOf(charCountOfShift) + solved);
		return solution;
	}

	public static String getMessageOfTheDay() {
		return null;	
	}

	public static void main(String[] args) throws IOException {
		System.out.println(get(CS_WEB_CAT_URL));
		System.out.println(get(("http://cswebcat.swansea.ac.uk/message?solution=" + solvePuzzle(get(CS_WEB_CAT_URL)))));
	}
}
