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

<<<<<<< Updated upstream
=======
	/**
	 * Instantiates a new player.
	 *
	 * @param playerRecord the player record
	 */
	public Player(String playerRecord) {
		String[] fields = playerRecord.split(",");
		this.playerID = Integer.valueOf(fields[0]);
		this.playerName = fields[1];
		this.maxLevelID = Integer.valueOf(fields[2]);
	}

	/**
	 * Gets the player info.
	 *
	 * @return the player info
	 */
	public String getPlayerInfo() {
		return this.getPlayerID() + "," + this.getPlayerName() + "," + this.getMaxLevelID();
	}

	/**
	 * Gets the player ID.
	 *
	 * @return the player ID
	 */
>>>>>>> Stashed changes
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