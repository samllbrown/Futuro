package managers;

/**
 * Player.java
 * @author Sam R,
 * @version 1
 * Last Mod Date: 30/11/2021
 */

/**
 * The Class Player handles all player information.
 */
public class Player {

	/** The player ID. */
	private int playerID;

	/** The max level ID. */
	private int maxLevelID;

	/** The player name. */
	private String playerName;

	/**
	 * Instantiates a new player.
	 *
	 * @param playerID   the player ID
	 * @param playerName the player name
	 * @param maxLevelID the max level ID
	 */
	public Player(int playerID, String playerName, int maxLevelID) {
		this.playerName = playerName;
		this.maxLevelID = maxLevelID;
		this.playerID = playerID;
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param playerRecord the player record
	 */
	public Player(String playerRecord) {
		String[] fields = playerRecord.split(",");
		this.playerID = Integer.valueOf(fields[0]);
		this.playerName = fields[1];
		// this.maxLevelID = Integer.valueOf(fields[2]);
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
	public int getPlayerID() {
		return this.playerID;
	}

	/**
	 * Sets the player ID.
	 *
	 * @param playerID the new player ID
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * Gets the max level ID.
	 *
	 * @return the max level ID
	 */
	public int getMaxLevelID() {
		return this.maxLevelID;
	}

	/**
	 * Sets the max level ID.
	 *
	 * @param maxLevelID the new max level ID
	 */
	public void setMaxLevelID(int maxLevelID) {
		this.maxLevelID = maxLevelID;
	}

	/**
	 * Gets the player name.
	 *
	 * @return the player name
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	 * Sets the player name.
	 *
	 * @param playerName the new player name
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * To string.
	 *
	 * @return all player information in a string
	 */
	@Override
	public String toString() {
		return (this.playerID + "," + this.playerName + "," + this.maxLevelID);
	}
}