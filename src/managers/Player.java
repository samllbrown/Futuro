package managers;

public class Player {
	private int playerID;
	private int maxLevelID;
	private String playerName;
	private String playerRecord;

	public Player(int playerID, String playerName, int maxLevelID) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.maxLevelID = maxLevelID;
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
		this.playerNamer = playerName;
	}
	
}