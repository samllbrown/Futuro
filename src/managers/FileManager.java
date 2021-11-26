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
 * @author David Terence-Abanulo, ...
 * @version 1.0
 * Last Mod Date: 21/11/2021
 */

public class FileManager {
	public static final File PLAYER_FILE = new File ("Players.txt");
	public static final File LEADERBOARD_FILE = new File ("Leaderboard.txt");

//	public static void writeObjectToFile(String fileName, Object object) throws IOException, FileNotFoundException {
//		// find the filename or something
//		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
//		bw.write(object.toString());
//		bw.close();
//	}

	// if the player already exists but the player record is now different from that which is in the file,
	// we need to delete the record from the file and add in the new record
	// this isn't being done here yet as far as i can tell
	// editing a file is simple - we delete and write again
	public static void writeToPlayerFile(Player player) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(PLAYER_FILE, true));
		bw.write(player.getPlayerRecord() + "\n");
		bw.close();
	}

	public static void writeToLeaderboardFile(Player player, int playerScore, int rank) throws IOException {
		String record = player.getPlayerID() + "," + playerScore + "," + rank;
		BufferedWriter bw = new BufferedWriter(new FileWriter(LEADERBOARD_FILE, true));
		bw.write(record + "\n");
		bw.close();
	}

	/**
	 * Given a playerID checks if that player exists within the player file, if so then returns that player
	 * @param playerID
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Player readPlayerFile (int playerID) throws IOException {
		String currentLine;
		String playerRecord = null;
		BufferedReader br = new BufferedReader(new FileReader(PLAYER_FILE));
		boolean found = false;
		int currentPlayerID;
		while((currentLine = br.readLine()) != null && (!found)) {
			if (Integer.valueOf(currentLine.split(",")[0]) == playerID) {
				playerRecord = currentLine;
				found = true;
			}
		}
		// HELLO DAVID
		// PLEASE COULD YOU WRITE SOME VALIDATION STUFF FOR THIS METHOD
		// THANK YOU
		// SAM
		Player player = new Player(playerRecord);
		return player;
//		Player selectedPlayer = null;
//		Scanner in = new Scanner(PLAYER_FILE);
//		ArrayList<Player> playersFromFile = new ArrayList<>();
//		while (in.hasNextLine()) {
//			String curLine = in.nextLine();
//			Scanner line = new Scanner(curLine).useDelimiter(",");
//			playersFromFile.add(new Player(line.nextInt(), line.next(), line.nextInt()));
//		}
//		for (Player p : playersFromFile) {
//			if (p.getPlayerID() == playerID) {
//				selectedPlayer = p;
//			}
//		}
		/*try {
			return selectedPlayer;
		} catch (NullPointerException e){
			System.out.println("Player doesn't exist!");
		}*/
//		return selectedPlayer;
	}

	/**
	 * Testing reading and writing stuff
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[]args) throws IOException {
		Player David = new Player(2,"David",2);
		Player Illia = new Player(3,"Illia",3);
		Player Sam = new Player(1,"Sam",5);
		//should return player 1 info
		System.out.println(FileManager.readPlayerFile(Illia.getPlayerID()));
		//player 3 doesn't exist so should return null
		System.out.println(FileManager.readPlayerFile(Sam.getPlayerID()));

		FileManager.writeToLeaderboardFile(David,100,5);
		FileManager.writeToPlayerFile(David);
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
		return new Level(levelid, height, width, inventory, numberOfMechsToLose, currentScore, expectedSecondsToComplete, elapsedTime, mechs, grid);
	}



	// HELLO DAVID THIS IS SAM
	// I DIDN'T KNOW WHETHER OR NOT YOU NEEDED HELP WITH THIS
	// SO I HAVE MADE A NEW METHOD FOR IT ABOVE
	// PLEASE REFER TO THE METHOD AND SEE WHAT IT IS DOING
	// DECIDE WHICH ONE YOU'D PREFER TO KEEP
	// PLEASE NOTE THAT THE ABOVE METHOD SHOULD WORK.
	// MANY THANKS
	// SAM
	// IGNORE THE MESS BELOW USING IT LATER - DAVID
	public static void readLevelFile(String filename) throws FileNotFoundException {
		String levelID;
		int score;
		int width;
		int height;
		int timeElasped;
		int completionTime;
		int respawnRate;
		int mechsToLose;
		String inventory;
		/*boardlayout, mech spawn, items in play - not sure how they're being stored but basically
		 create a temp variable for it to be stored in e.g an Array thats
		 passed into Board b = new Board (BoardArray) or something like that maybe*/

		File levelFile = new File (filename);
		Scanner in = new Scanner (levelFile);
		while (in.hasNextLine()){
			String curLine = in.nextLine();
			Scanner line = new Scanner(curLine).useDelimiter(",/n,"); //depends on what delimiter we use for now leaving it as ",/n,"
			levelID = line.next();
			score = line.nextInt();
			width = line.nextInt();
			height = line.nextInt();
			//board layout = ....
			//mech spawn = ....
			//items in play =....
			timeElasped = line.nextInt();
			completionTime = line.nextInt();
			respawnRate = line.nextInt();
			mechsToLose = line.nextInt();
			inventory = line.next();
			/*or for items like respawnRate, board etc, could make a method e.g getRespawnRateFromFile(Scanner in)
			where Scanner in would be in, then do the String curLine thing where line = new Scanner(curLine).useDelimiter(",")
			cos respawnRate = 20,25,15,15,15,5,5
			 */
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
	}

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
}
