package managers;

import board.Level;

public class Player {
	private int playerID;
	private int maxLevelID;
	private String playerName;

	public Player(int playerID, String playerName, int maxLevelID) {
		this.playerName = playerName;
		this.maxLevelID = maxLevelID;
		this.playerID = playerID;
	}


	// HERE WE ARE DEFINING A DIFFERENT SORT OF CONSTRUCTOR WHICH I PERSONALLY FIND QUITE RIVETTING
	// PERHAPS WE COULD DO THIS FOR OTHER SORTS OF OBJECTS AS WELL
	// I THINK THIS IS A GOOD IDEA POSSIBLY
	// PLEASE COULD ANYONE READING THIS COMMENT GET BACK TO ME ON THEIR THOUGHTS ABOUT THIS
	// MANY THANKS
	// SAM R
	// XOXOXOXOXOXOXOXOXOXO
	public Player(String playerRecord) {
		String[] fields = playerRecord.split(",");
		this.playerID = Integer.valueOf(fields[0]);
		this.playerName = fields[1];
		//this.maxLevelID = Integer.valueOf(fields[2]);
	}

	public String getPlayerInfo() {
		return this.getPlayerID() + "," + this.getPlayerName() + "," + this.getMaxLevelID();
	}

	public int getPlayerID() {
		return this.playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID; 
	}

	public int getMaxLevelID() {
		return this.maxLevelID;
	}

	public void setMaxLevelID(int maxLevelID) {
		this.maxLevelID = maxLevelID;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		return (this.playerID+","+this.playerName+","+this.maxLevelID);
	}
}