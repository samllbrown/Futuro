package services;

import java.io.IOException;
import java.util.HashMap;

/**
 * MessageOfTheDay.java
 * @author Sam R
 */

public class MessageOfTheDay {

    private static final String CS_WEB_CAT_URL = "http://cswebcat.swansea.ac.uk/";

    /** The Constant GET_MESSAGE_URL. */
    private static final String GET_MESSAGE_URL = "http://cswebcat.swansea.ac.uk/message?solution=";

    /** The Constant ALPHA. */
    private static final char[] ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /** The Constant MAX_ALPHA_INDEX. */
    private static final int MAX_ALPHA_INDEX = 25;

    /** The Constant ALPHA_LENGTH. */
    private static final int ALPHA_LENGTH = 26;

    /**
     * Gets the puzzle code.
     *
     * @return the puzzle code
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static String getPuzzleCode() throws IOException {
	return Network.getRequest(CS_WEB_CAT_URL + "puzzle");
    }

    /**
     * Gets the message with solution.
     *
     * @param solution the solution
     * @return the message with solution
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static String getMessageWithSolution(String solution) throws IOException {
	return Network.getRequest(GET_MESSAGE_URL + solution);
    }

    /**
     * Gets the shifted char.
     *
     * @param index the index
     * @param direction the direction
     * @param shift the shift
     * @return the shifted char
     */
    private static String getShiftedChar(int index, int direction, int shift) {
	int shiftBy = direction * shift;
	shiftBy = shiftBy < 0 ? shiftBy + ALPHA_LENGTH : shiftBy;
	int alphaIndex = ((index + shiftBy) % ALPHA_LENGTH);
	return String.valueOf(ALPHA[alphaIndex]);
    }

    /**
     * Shift chars in puzzle.
     *
     * @param puzzleCode the puzzle code
     * @return the shifted string
     */
    private static String shiftCharsInPuzzle(String puzzleCode) {
	String shifted = "";

	int dir = -1;

	HashMap<String, Integer> letterToIndex = new HashMap<>();

	for (int i = 0; i < ALPHA_LENGTH; i++) {
	    letterToIndex.put(String.valueOf(ALPHA[i]), Integer.valueOf(i));
	}

	for (int i = 0; i < puzzleCode.length(); i++) {
	    shifted += getShiftedChar(letterToIndex.get(String.valueOf(puzzleCode.charAt(i))), dir, (i + 1));
	    dir = (dir * -1);
	}
	return shifted;
    }

    /**
     * Solve puzzle of the inputted string.
     *
     * @param puzzleCode - the puzzle code
     * @return the string
     */
    private static String solvePuzzle(String puzzleCode) {
	String shiftedCode = shiftCharsInPuzzle(puzzleCode);
	shiftedCode += "CS-230";
	int currentLength = shiftedCode.length();
	return (String.valueOf(currentLength) + shiftedCode);
    }

    /**
     * Gets the message of the day.
     *
     * @return the message of the day
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String getMessageOfTheDay() throws IOException {
	String puzzleCode = getPuzzleCode();
	String solution = solvePuzzle(puzzleCode);
	return getMessageWithSolution(solution);
    }
}
