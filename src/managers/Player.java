package managers;

import board.Level;

public class Player {
	private int playerID;
	private int maxLevelID;
	private String playerName;
	private String playerRecord;
	private static int nextPlayerID;

	public Player(int playerID, String playerName, int maxLevelID) {
		this.playerName = playerName;
		this.maxLevelID = maxLevelID;
		this.playerID = Player.nextPlayerID;
		Player.nextPlayerID++;
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
	
}