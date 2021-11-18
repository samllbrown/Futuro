package managers;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;

import board.Level; 

public class FileManager {
	
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
		return new Level(0, 0, null, 0, 0, 0, 0, null);
	}
	
	public static void writeFile() {
		
	}

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

	public static void readPlayerFile (String filename, Player p) throws FileNotFoundException {
		File playerFile = new File (filename);
		Scanner in = new Scanner (playerFile);
		while(in.hasNextInt()){
			if (in.equals(p.getPlayerID())){
				/*load the level from the player if they exist...idk how we're doing that right now but yeah*/
			} else {
				//return error player doesn't exist -> must create a new player
			}
		}
	}
}
