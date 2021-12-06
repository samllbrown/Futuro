package managers;

import java.io.*;
import java.util.*;

import board.Grid;
import board.Level;
import gameObject.GameObjectFactory;
import gameObject.Item;
import gameObject.Mech;
import inventory.Inventory;

/**
 * FileManager.java
 * @author David Terence-Abanulo, Sam R, Sam B
 * @version 1.5
 * Last Mod Date: 27/11/2021
 */

public class FileManager {
    public static final File PLAYER_FILE = new File("res/Players/Players.txt");
    public static final File LEADERBOARD_FILE = new File("res/Leaderboard.txt");

    /**
     * reads leaderboard
     * @param levelId level ID to read
     * @return leaderboard
     */
    public Leaderboard readLeaderBoard(int levelId) {
	HashMap<Integer, Integer> leaderBoardHashMap = new HashMap<>();
	BufferedReader br = null;

	try {
	    String levelFile = String.format("res/LeaderboardFiles/LEVEL_%dlb.txt", levelId);
	    br = new BufferedReader(new FileReader(new File(levelFile)));
	    String currentLine;
	    while ((currentLine = br.readLine()) != null) {
		int playerID = Integer.valueOf(currentLine.split(",")[0]);
		int score = Integer.valueOf(currentLine.split(",")[1]);
		leaderBoardHashMap.put(playerID, score);
	    }
	} catch (IOException e) {
	    System.err.println("There was an error reading this file");
	} finally {
	    try {
		br.close();
	    } catch (IOException e) {
		System.err.println(
			"There was an error closing the BufferedReader whilst retrieving the leaderboard file");
	    }
	}
	return new Leaderboard(levelId, leaderBoardHashMap);
    }

    /**
     * writes a record to the file
     * @param record to write
     * @param file to write to
     */
    private static void writeRecordToFile(String record, File file) {
	BufferedWriter bw = null;
	try {
	    bw = new BufferedWriter(new FileWriter(file, true));
	    bw.write(record + "\n");
	} catch (IOException e) {
	    System.err.println(String.format("Could not write record: %s to file: %s", record, file.getName()));
	} finally {
	    if (bw != null) {
		try {
		    bw.flush();
		    bw.close();
		} catch (IOException e) {
		    System.err.println("Error flushing and closing BufferedWriter");
		}
	    }
	}
    }

    /**
     * reads record with an id
     * @param id to look at
     * @param file to look at
     * @return the reocrd with the ID
     */
    public static String getRecordWithID(int id, File file) {
	BufferedReader br = null;
	String returnLine = null;
	boolean found = false;
	try {
	    br = new BufferedReader(new FileReader(file));
	    String currentLine;
	    String[] currentLineSplit;
	    while ((currentLine = br.readLine()) != null && !found) {
		currentLineSplit = currentLine.split(",");
		if (Integer.valueOf(currentLineSplit[0]) == id) {
		    found = true;
		    returnLine = currentLine;
		}
	    }
	} catch (IOException e) {
	    System.err.println("");
	} finally {
	    try {
		br.close();
	    } catch (IOException e) {
		System.err.println("There was an error closing the BufferedReader whilst retrieving a record");
	    }
	}
	System.out.println("READ RECORD: " + returnLine);
	return returnLine;
    }

    /**
     * deletes record with ID
     * @param id id to look at
     * @param file file to look at
     */
    public static void deleteRecordWithID(int id, File file) {
	BufferedReader br = null;
	BufferedWriter wr = null;
	File newFile = new File("res/temp.txt");
	try {
	    br = new BufferedReader(new FileReader(file));
	    wr = new BufferedWriter(new FileWriter(newFile));
	    String currentLine;
	    while ((currentLine = br.readLine()) != null) {
		int currentID = Integer.valueOf(currentLine.split(",")[0]);
		if (!(id == currentID)) {
		    wr.write(currentLine + "\n");
		}
	    }
	} catch (IOException e) {
	    System.err.println(String.format("Error deleting record with id: %d in file: %s", id, file.getName()));
	    System.err.println(e.toString());
	} finally {
	    try {
		br.close();
		wr.flush();
		wr.close();
		if (file.delete()) {
		    newFile.renameTo(file);
		} else {
		    throw new IOException("Could not delete original file");
		}

	    } catch (IOException e) {
		System.err.println("Error flushing and closing readers and/or writers whilst deleting record");
	    }
	}

    }

    /**
     * gets all ids from file
     * @param file file to be looked at
     * @return all ids
     */
    public static HashSet<Integer> getAllIdsInFile(File file) {
	HashSet<Integer> ids = new HashSet<>();
	BufferedReader br = null;
	try {
	    br = new BufferedReader(new FileReader(file));
	    String currentLine;
	    String[] currentLineSplit;
	    while ((currentLine = br.readLine()) != null) {
		currentLineSplit = currentLine.split(",");
		ids.add(Integer.valueOf(currentLineSplit[0]));
	    }
	} catch (IOException e) {
	    System.err.println("");
	} finally {
	    try {
		br.close();
	    } catch (IOException e) {
		System.err.println("There was an error closing the BufferedReader");
	    }
	}
	return ids;
    }

    /**
     * writes player to file
     * @param player player to be written
     * @return boolean
     */
    public static boolean writeToPlayerFile(Player player) {
	boolean written = false;
	HashSet<Integer> playerids = getAllIdsInFile(PLAYER_FILE);
	if (!(playerids.contains(player.getPlayerID()))) {
	    writeRecordToFile(player.toString(), PLAYER_FILE);
	    written = true;
	}
	return written;
    }

    /**
     * deletes player from file
     * @param player player to be deleted
     * @return boolean
     */
    public static boolean deleteFromPlayerFile(Player player) {
	boolean deleted = false;
	HashSet<Integer> playerids = getAllIdsInFile(PLAYER_FILE);
	System.out.println(playerids.contains(player.getPlayerID()));
	if ((playerids.contains(player.getPlayerID()))) {
	    deleteRecordWithID(player.getPlayerID(), PLAYER_FILE);
	    deleted = true;
	}
	return deleted;
    }

    /**
     * gets all info of player
     * @param playerID id of player
     * @return players info
     */
    public static String getPlayerInfo(int playerID) {
	String playerInfo = getRecordWithID(playerID, PLAYER_FILE);
	return playerInfo;

    }

    /**
     * gets player
     * @param playerID id of player
     * @return player
     */
    public static Player getPlayer(int playerID) {
	String playerInfo = getPlayerInfo(playerID);
	if (playerInfo == null) {
	    return null;
	} else {
	    return new Player(playerInfo);
	}
    }

    /**
     * reads a level from a file
     * @param fileName name of file
     * @return the level in an object
     * @throws Exception
     */
    public static Level readLevel(String fileName) throws Exception {
	BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
	String currentLine;
	int levelid, width, height, numberOfMechs, numberOfItemsInPlay, numberOfItemsInInventory, currentScore,
		elapsedTime, numberOfMechsToLose, expectedSecondsToComplete, itemRespawn;

	String tiles = "";
	ArrayList<Mech> mechs = new ArrayList<>();
	ArrayList<Item> itemsInPlay = new ArrayList<>();
	Inventory inventory = new Inventory();
	levelid = Integer.valueOf(br.readLine());
	width = Integer.valueOf(br.readLine());
	height = Integer.valueOf(br.readLine());
	Grid grid = new Grid(width, height);
	for (int i = 0; i < height; i++) {
	    tiles += br.readLine() + "\n";

	}

	numberOfMechs = Integer.valueOf(br.readLine());

	for (int i = 0; i < numberOfMechs; i++) {
	    mechs.add(GameObjectFactory.readMech(br.readLine()));
	}

	numberOfItemsInPlay = Integer.valueOf(br.readLine());
	for (int i = 0; i < numberOfItemsInPlay; i++) {
	    itemsInPlay.add(GameObjectFactory.readItem(br.readLine()));
	}

	numberOfItemsInInventory = Integer.valueOf(br.readLine());
	System.out.println(numberOfItemsInInventory);
	for (int i = 0; i < numberOfItemsInInventory; i++) {
	    inventory.addItem(GameObjectFactory.readInventoryItem(br.readLine()));
	}
	currentScore = Integer.valueOf(br.readLine());
	elapsedTime = Integer.valueOf(br.readLine());
	expectedSecondsToComplete = Integer.valueOf(br.readLine());
	numberOfMechsToLose = Integer.valueOf(br.readLine());
	itemRespawn = Integer.valueOf(br.readLine());
	grid.populateGrid(tiles);
	br.close();

	return new Level(levelid, inventory, numberOfMechsToLose, currentScore, expectedSecondsToComplete, elapsedTime,
		mechs, grid, itemRespawn);
    }

    /**
     * writes to a level file when saving
     * @param level the level that will be saved
     * @param forPlayer the player that saved the level
     */
    public static void writeLevel(Level level, Player forPlayer) {
	int width = level.getGrid().getWidth();
	int height = level.getGrid().getHeight();

	Grid grid = level.getGrid();

	String tiles = grid.toString();
	String recentSaveFileName = String.format("res/Players/%d_lastSave.txt", forPlayer.getPlayerID());

	BufferedWriter bw = null;
	try {
	    bw = new BufferedWriter(new FileWriter(new File(recentSaveFileName), false));
	    bw.write(level.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (bw != null) {
		try {
		    bw.flush();
		    bw.close();
		} catch (Exception e) {
		    System.err.println("Error flushing and closing buffered writers");
		}
	    }
	}
    }
}