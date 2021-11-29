package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import board.Grid;
import board.Level;
import gameObject.GameObjectFactory;
import gameObject.Item;
import gameObject.Mech;
import inventory.Inventory;

/**
 * FileManager.java
 * @author David Terence-Abanulo, Sam R
 * @version 1.5
 * Last Mod Date: 27/11/2021
 */

public class FileManager {
	public static final File PLAYER_FILE = new File ( "Players.txt");
	public static final File LEADERBOARD_FILE = new File ("Leaderboard.txt");

	// need to do leaderboard reading and writing or something

	private static void writeRecordToFile(String record, File file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(record + "\n");
//			bw.flush();
//			bw.close();
		} catch(IOException e) {
			System.err.println(String.format("Could not write record: %s to file: %s", record, file.getName()));
		} finally {
			if(bw != null ) {
				try {
					bw.flush();
					bw.close();
				} catch(IOException e) {
					System.err.println("Error flushing and closing BufferedWriter");
				}
			}
		}
	}

//	private static void writeRecordToFile(String record, File file) throws IOException {
//		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
//		bw.write(record + "\n");
//		bw.flush();
//		bw.close();
//	}

	public static String getRecordWithID(int id, File file) {
		BufferedReader br = null;
		String returnLine = null;
		boolean found = false;
		try {
			br = new BufferedReader(new FileReader(file));
			String currentLine;
			String[] currentLineSplit;
			while((currentLine = br.readLine()) != null && !found) {
				currentLineSplit = currentLine.split(",");
				if(Integer.valueOf(currentLineSplit[0]) == id) {
					found = true;
					returnLine = currentLine;
				}
			}
		} catch(IOException e) {
			System.err.println("");
		} finally {
			try {
				br.close();
			} catch(IOException e) {
				System.err.println("There was an error closing the BufferedReader whilst retrieving a record");
			}
		}
		return returnLine;
	}
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String currentLine;
//		String[] lineSplit;
//		while((currentLine = br.readLine()) != null) {
//			lineSplit = currentLine.split(",");
//			if(Integer.valueOf(lineSplit[0]) == id) {
//				br.close();
//				return currentLine;
//			}
//		}
//		br.close();
//		return null;

	public static void deleteRecordWithID(int id, File file) {
		BufferedReader br = null;
		BufferedWriter wr = null;
		File newFile = new File("temp.txt");
		try {
			br = new BufferedReader(new FileReader(file));
			wr = new BufferedWriter(new FileWriter(newFile));
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				int currentID = Integer.valueOf(currentLine.split(",")[0]);
				if(!(id == currentID)) {
					wr.write(currentLine + "\n");
				}
			}
		} catch(IOException e) {
			System.err.println(String.format("Error deleting record with id: %d in file: %s", id, file.getName()));
			System.err.println(e.toString());
		} finally {
			try {
				br.close();
				wr.flush();
				if(file.delete()) {
					newFile.renameTo(file);
				} else {
					throw new IOException("Could not delete original file");
				}

			} catch(IOException e) {
				System.err.println("Error flushing and closing readers and/or writers whilst deleting record");
			}
		  }
		
	}

	
	private static boolean recordRepeatedInFile(int record, File file) {
		Scanner sc = null;
		boolean recorded = false;
		try {
			 sc = new Scanner(PLAYER_FILE);
			 while (sc.hasNextLine() && recorded == false) {
				 String[] line = (sc.nextLine()).split(",");
				 recorded = (Integer.valueOf(line[0]) == record);
			 }
			 
		   	 sc.close();
		} catch(IOException e) {
			System.err.println(String.format("Could not write record: %s to file: %s", record, file.getName()));
		} 
		return recorded;
	}
	
//		System.err.println("Attempting to delete record with ID: " + id);
//		File newFile = new File("temp.txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedWriter wr = new BufferedWriter(new FileWriter(newFile));
//		String currentLine;
//
//		while((currentLine = br.readLine()) != null) {
//			if(!(id == Integer.valueOf(currentLine.split(",")[0]))) {
//				wr.write(currentLine + "\n");
//			}
//		}
//		br.close();
//		wr.flush();
//		wr.close();
//		file.delete();
//		newFile.renameTo(file);
	

	// probably needs validation
	public static String getPlayerInfo(int playerID) {
		return getRecordWithID(playerID, PLAYER_FILE);
	}

	public static Player getPlayer(int playerID) {
		return new Player(getPlayerInfo(playerID));
	}

	public boolean writeToPlayerFile(Player player) {
		boolean writen = false;
		if(recordRepeatedInFile(player.getPlayerID(), PLAYER_FILE) == false) {
			writeRecordToFile(player.toString(), PLAYER_FILE);
			writen = true;
		}
		return writen;
	}
//
//	public static void writeToPlayerFile(Player player) throws IOException {
//		boolean found = false;
//		try {
//		    Scanner scanner = new Scanner(PLAYER_FILE);
//		    BufferedWriter bw = new BufferedWriter(new FileWriter(PLAYER_FILE));
//		    int lineNum = 0;
//
//		    while (scanner.hasNextLine() && found == false) {
//		        String line = scanner.nextLine();
//		        lineNum++;
//		        if (line.contains(Integer.toString(player.getPlayerID()))) {
//		        	System.out.println(line);
//		        	bw.write(line + System.getProperty("line.separator"));
//		        	found = true;
//		        }
//		    }
//		    bw.flush();
//		    bw.close();
//		} catch(FileNotFoundException e) {
//		    //handle this
//		}
//		if(found == false) {
//			writeRecordToFile(player.getPlayerInfo(), PLAYER_FILE);
//		}
//	}

//	public static void writeToLeaderboardFile(Player player, int playerScore, int rank) throws IOException {
//		// put this into a method plz
//		String record = player.getPlayerID() + "," + playerScore + "," + rank;
//		writeRecordToFile(record, LEADERBOARD_FILE);
//	}
	
	/**
	 * Given a playerID checks if that player exists within the player file, if so then returns that player
	 * @param playerID
	 * @return
	 * @throws FileNotFoundException
	 */
//	public static Player checkIfPlayerExists (int playerID) throws Exception {
//		String playerRecord = getPlayerInfoFromFile(playerID, PLAYER_FILE);
//		if(playerRecord == null) {
//			throw new Exception("Could not find PlayerID: " + playerID);
//		} else {
//			return new Player(playerRecord);
//		}
//	}


	public static Level readLevel(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		String currentLine;
		int levelid, width, height, numberOfMechs, numberOfItemsInPlay, numberOfItemsInInventory, currentScore, elapsedTime, numberOfMechsToLose, expectedSecondsToComplete;
		//ArrayList<String> rowsOfTiles = new ArrayList<>();
		String tiles = "";
		ArrayList<Mech> mechs = new ArrayList<>();
		ArrayList<Item> itemsInPlay = new ArrayList<>();
		Inventory inventory = new Inventory();
		levelid = Integer.valueOf(br.readLine());
		width = Integer.valueOf(br.readLine());
		height = Integer.valueOf(br.readLine());
		Grid grid = new Grid(width, height);
		for(int i = 0; i < height; i++) {
			tiles += br.readLine() + "\n";
			//rowsOfTiles.add(br.readLine());
		}

		numberOfMechs = Integer.valueOf(br.readLine());

		for(int i = 0; i < numberOfMechs; i++) {
			mechs.add(GameObjectFactory.readMech(br.readLine()));
		}

		numberOfItemsInPlay = Integer.valueOf(br.readLine());
		for(int i = 0; i < numberOfItemsInPlay; i++) {
			itemsInPlay.add(GameObjectFactory.readItem(br.readLine()));
		}

		numberOfItemsInInventory = Integer.valueOf(br.readLine());
		for(int i = 0; i < numberOfItemsInInventory; i++) {
			inventory.addItem(GameObjectFactory.readInventoryItem(br.readLine()));
		}
		currentScore = Integer.valueOf(br.readLine());
		elapsedTime = Integer.valueOf(br.readLine());
		expectedSecondsToComplete = Integer.valueOf(br.readLine());
		numberOfMechsToLose = Integer.valueOf(br.readLine());
		grid.populateGrid(tiles);
		br.close();
		// null for inventory for now;
		return new Level(levelid, height, width, null, numberOfMechsToLose, currentScore, expectedSecondsToComplete, elapsedTime, mechs, grid);
	}

	/*
	* LEVEL FILE FORMAT ONCE AND FOR ALL:
	* LEVELID
	* WIDTH
	* HEIGHT
	* TILEROW_1
	* TILEROW_2
	* ........
	* TILEROW_HEIGHT
	* NUMBEROFMECHS
	* MECHID_1
	* MECHID_2
	* ......
	* MECHID_NUMBEROFMECHS
	* NUMBEROFITEMSINPLAY
	* ITEMID_1
	* ITEMID_2
	* .....
	* ITEMID_NUMBEROFITEMSINPLAY
	* NUMBEROFITEMSININVENTORY
	* INV_ITEM_1
	* INV_ITEM_2
	* .......
	* CURRENTSCORE
	* ELAPSEDTIME
	* EXPECTEDTIME
	* NUMBEROFMECHSTOLOSE
	* */
	// need to do try-catches in here
//	public static Level readLevel(String fileName) throws Exception {
//		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
//		String currentLine;
//		int levelid, width, height, numberOfMechs, numberOfItemsInPlay, numberOfItemsInInventory, currentScore, elapsedTime, numberOfMechsToLose, expectedSecondsToComplete;
//		//ArrayList<String> rowsOfTiles = new ArrayList<>();
//		String tiles = "";
//		ArrayList<Mech> mechs = new ArrayList<>();
//		ArrayList<Item> itemsInPlay = new ArrayList<>();
//		Inventory inventory = new Inventory();
//		levelid = Integer.valueOf(br.readLine());
//		width = Integer.valueOf(br.readLine());
//		height = Integer.valueOf(br.readLine());
//		Grid grid = new Grid(width, height);
//		for(int i = 0; i < height; i++) {
//			tiles += br.readLine() + "\n";
//			//rowsOfTiles.add(br.readLine());
//		}
//
//		numberOfMechs = Integer.valueOf(br.readLine());
//
//		for(int i = 0; i < numberOfMechs; i++) {
//			mechs.add(GameObjectFactory.readMech(br.readLine()));
//		}
//
//		numberOfItemsInPlay = Integer.valueOf(br.readLine());
//		for(int i = 0; i < numberOfItemsInPlay; i++) {
//			itemsInPlay.add(GameObjectFactory.readItem(br.readLine()));
//		}
//
//		numberOfItemsInInventory = Integer.valueOf(br.readLine());
//		for(int i = 0; i < numberOfItemsInInventory; i++) {
//			inventory.addItem(GameObjectFactory.readInventoryItem(br.readLine()));
//		}
//		currentScore = Integer.valueOf(br.readLine());
//		elapsedTime = Integer.valueOf(br.readLine());
//		expectedSecondsToComplete = Integer.valueOf(br.readLine());
//		numberOfMechsToLose = Integer.valueOf(br.readLine());
//		grid.populateGrid(tiles);
//		br.close();
//		return new Level(levelid, height, width, inventory, numberOfMechsToLose, currentScore, expectedSecondsToComplete, elapsedTime, mechs, grid);
//	}



	// HELLO DAVID THIS IS SAM
	// I DIDN'T KNOW WHETHER OR NOT YOU NEEDED HELP WITH THIS
	// SO I HAVE MADE A NEW METHOD FOR IT ABOVE
	// PLEASE REFER TO THE METHOD AND SEE WHAT IT IS DOING
	// DECIDE WHICH ONE YOU'D PREFER TO KEEP
	// PLEASE NOTE THAT THE ABOVE METHOD SHOULD WORK.
	// MANY THANKS
	// SAM
	// IGNORE THE MESS BELOW USING IT LATER - DAVID
//	public static void readLevelFile(String filename) throws FileNotFoundException {
//		String levelID;
//		int score;
//		int width;
//		int height;
//		int timeElasped;
//		int completionTime;
//		int respawnRate;
//		int mechsToLose;
//		String inventory;
//		/*boardlayout, mech spawn, items in play - not sure how they're being stored but basically
//		 create a temp variable for it to be stored in e.g an Array thats
//		 passed into Board b = new Board (BoardArray) or something like that maybe*/
//
//		File levelFile = new File (filename);
//		Scanner in = new Scanner (levelFile);
//		while (in.hasNextLine()){
//			String curLine = in.nextLine();
//			Scanner line = new Scanner(curLine).useDelimiter(",/n,"); //depends on what delimiter we use for now leaving it as ",/n,"
//			levelID = line.next();
//			score = line.nextInt();
//			width = line.nextInt();
//			height = line.nextInt();
//			//board layout = ....
//			//mech spawn = ....
//			//items in play =....
//			timeElasped = line.nextInt();
//			completionTime = line.nextInt();
//			respawnRate = line.nextInt();
//			mechsToLose = line.nextInt();
//			inventory = line.next();
//			/*or for items like respawnRate, board etc, could make a method e.g getRespawnRateFromFile(Scanner in)
//			where Scanner in would be in, then do the String curLine thing where line = new Scanner(curLine).useDelimiter(",")
//			cos respawnRate = 20,25,15,15,15,5,5
//			 */
//		}
//	}
}
	
	/*Additional code for making an instance of a level from this file and Items in the inventory
	Level l1 = new Level (LevelID)
	  ArrayList<String> itemsFromFile = inventory.split(" ")
	  ArrayList<Items> itemsList;
	  for (String item:itemFromFile){
	  	new Item (item);
	  	itemsList.add(item)
	  	}
	  Inventory I1 = new Inventory()
	  for(Item i: itemsList){
	  	I1.items.add(i)
	  	}*/

//	/**
//	 *
//	 * @param levelName
//	 * @return
//	 */
//	public static Level readLevel(String levelName) {
//		String out = null;
//		try {
//			File level = new File(levelName);
//			Scanner reader = new Scanner(level);
//			while (reader.hasNextLine()) {
//				out = out + reader.nextLine();
//			}
//			reader.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("An error occurred.");
//			e.printStackTrace();
//		}
//		return new Level(10, 10, 10, null, 0, 10, 0, 0, null, null);
//	}
