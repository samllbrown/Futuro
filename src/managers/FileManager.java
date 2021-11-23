package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import board.Level;

/**
 * FileManager.java
 * @author David Terence-Abanulo, ...
 * @version 1.0
 * Last Mod Date: 21/11/2021
 */

public class FileManager {
	public static final File PLAYER_FILE = new File ("C:\\Users\\ultim\\Documents\\GitHub\\Futuro\\Players.txt");
	public static final File LEADERBOARD_FILE = new File ("");

	/**
	 *
	 * @param levelName
	 * @return
	 */
	public static Level readLevel(String levelName) {
		String out = null;
		try {
		      File level = new File(levelName);
		      Scanner reader = new Scanner(level);
		      while (reader.hasNextLine()) {
		    	  out = out + reader.nextLine();
		      }
	    reader.close();
	    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
	    }
		return new Level(10, 10, 10, null, 0, 10, 0, 0, null, null);
	}

	public static void writeObjectToFile(String fileName, Object object) throws IOException, FileNotFoundException {
		// find the filename or something
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
		bw.write(object.toString());
	}

	public static void writeToPlayerFile(Player player) throws FileNotFoundException {
		Scanner in = new Scanner(PLAYER_FILE);
	}

	public static void writeToLeaderboardFile(){}

	/**
	 * Given a playerID checks if that player exists within the player file, if so then returns that player
	 * @param playerID
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Player readPlayerFile (int playerID) throws FileNotFoundException {
		Player selectedPlayer = null;
		Scanner in = new Scanner(PLAYER_FILE);
		ArrayList<Player> playersFromFile = new ArrayList<>();
		ArrayList<Integer> playerIDsFromFile = new ArrayList<>();
		while (in.hasNextLine()) {
			String curLine = in.nextLine();
			Scanner line = new Scanner(curLine).useDelimiter(",");
			playersFromFile.add(new Player(line.nextInt(), line.next(), line.nextInt()));
		}
		for (Player p : playersFromFile) {
			if (p.getPlayerID() == playerID) {
				selectedPlayer = p;
			}
		}
		/*try {
			return selectedPlayer;
		} catch (NullPointerException e){
			System.out.println("Player doesn't exist!");
		}*/
		return selectedPlayer;
	}

	/**
	 * Testing reading and writing stuff
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[]args) throws FileNotFoundException {
		//should return player 1 info
		System.out.println(FileManager.readPlayerFile(1));
		//player 3 doesn't exist so should return null
		System.out.println(FileManager.readPlayerFile(3));
	}


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
}
