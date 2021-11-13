import java.io.IOException;
import java.util.HashMap;

public class MessageOfTheDay {
	private static final String CS_WEB_CAT_URL = "http://cswebcat.swansea.ac.uk/";
	private static final String GET_MESSAGE_URL = "http://cswebcat.swansea.ac.uk/message?solution=";
	
	private static final char[] ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final int MAX_ALPHA_INDEX = 25;
	private static final int ALPHA_LENGTH = 26;
	
	private static String getPuzzleCode() throws IOException {
		return Network.getRequest(CS_WEB_CAT_URL + "puzzle");
	}
	
	private static String getMessageWithSolution(String solution) throws IOException {
		return Network.getRequest(GET_MESSAGE_URL + solution);
	}
	
	private static String getShiftedChar(int index, int direction, int shift) {
		int shiftBy = direction * shift;
		shiftBy = shiftBy < 0 ? shiftBy + ALPHA_LENGTH : shiftBy;
		int alphaIndex = ((index + shiftBy)%ALPHA_LENGTH);
		return String.valueOf(ALPHA[alphaIndex]);
	}
	
	private static String shiftCharsInPuzzle(String puzzleCode) {
		String shifted = "";
		
		int dir = -1;
		
		HashMap<String, Integer> letterToIndex = new HashMap<>();
		
		for(int i = 0; i < ALPHA_LENGTH; i++) {
			letterToIndex.put(String.valueOf(ALPHA[i]), Integer.valueOf(i));
		}
		
		for(int i = 0; i < puzzleCode.length(); i++) {
			shifted += getShiftedChar(letterToIndex.get(String.valueOf(puzzleCode.charAt(i))), dir, (i+1));
			dir = (dir * -1);
		}
		return shifted;
	}
	
	private static String solvePuzzle(String puzzleCode) {
		String shiftedCode = shiftCharsInPuzzle(puzzleCode);
		shiftedCode += "CS-230";
		int currentLength = shiftedCode.length();
		return (String.valueOf(currentLength) + shiftedCode);
	}
	
	public static String getMessageOfTheDay() throws IOException {
		String puzzleCode = getPuzzleCode();
		String solution = solvePuzzle(puzzleCode);
		return getMessageWithSolution(solution);
	}
}
