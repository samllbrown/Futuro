package managers;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;

import board.Level; 

public class FileManager {
<<<<<<< Updated upstream
	
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
=======
	private final static File PLAYER_FILE;
	private final static File LEADERBOARD_FILE;


	
	private static void writeRecordToFile(String record, File file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		bw.write(record + "\n");
		bw.flush(); 
		bw.close();
	}
	
	private String getCurrentWorkingDirectory() {
        String userDirectory = System.getProperty("user.dir");
        return userDirectory;
    }

	// probably needs validation
	public static String getPlayerInfoFromFile(int playerID) throws IOException {
		// search algo might be useful here, probs best to store the records in a sorted order.
		String currentLine;
		BufferedReader br = new BufferedReader(new FileReader(PLAYER_FILE));
		while((currentLine = br.readLine()) != null) {
			int currentID = Integer.valueOf(currentLine.split(",")[0]);
			if (currentID == playerID) {
				return currentLine;
			}
		}
		// shouldn't be doing this throw an exception or something instead.
		return currentLine;
	}
	
	public void writeToPlayerFile(Player player) throws IOException {
		boolean found = false;
		try {
		    Scanner scanner = new Scanner(PLAYER_FILE);
		    BufferedWriter bw = new BufferedWriter(new FileWriter(PLAYER_FILE));
		    int lineNum = 0;
		    
		    System.out.println(scanner.hasNextLine());
		    while (scanner.hasNextLine() && found == false) {
		        String line = scanner.nextLine();
		        lineNum++;
		        if (line.contains(Integer.toString(player.getPlayerID()))) {
		        	System.out.println(line);
		        	bw.write(line + System.getProperty("line.separator"));
		        	found = true;
		        }
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
		}
		if(found == false) {
			writeRecordToFile(player.getPlayerInfo(), PLAYER_FILE);
		}
	}

	public void writeToLeaderboardFile(Player player, int playerScore, int rank) throws IOException {
		// put this into a method plz
		String record = player.getPlayerID() + "," + playerScore + "," + rank;
		writeRecordToFile(record, LEADERBOARD_FILE);
	}
	
	/**
	 * Given a playerID checks if that player exists within the player file, if so then returns that player
	 * @param playerID
	 * @return
	 * @throws FileNotFoundException
	 */
	/*
	public static Player checkIfPlayerExists (int playerID) throws Exception {
		String playerRecord = getPlayerInfoFromFile(playerID, PLAYER_FILE);
		if(playerRecord == null) {
			throw new Exception("Could not find PlayerID: " + playerID);
		} else {
			return new Player(playerRecord);
		}
	}
	*/
	
	/**
	 * Testing reading and writing stuff
	 * @param args
	 * @throws FileNotFoundException
	 */
	/*
	public static void main(String[]args) throws Exception {
		Player David = new Player(2,"David",2);
		Player Illia = new Player(3,"Illia",3);
		Player Sam = new Player(1,"Sam",5);
		//should return player 1 info
		System.out.println(FileManager.readPlayerFile(Illia.getPlayerID()));
		//player 3 doesn't exist so should return null
		System.out.println(FileManager.readPlayerFile(Sam.getPlayerID()));

		FileManager.writeToLeaderboardFile(David,100,5);
		FileManager.writeToPlayerFile(David);
>>>>>>> Stashed changes
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
