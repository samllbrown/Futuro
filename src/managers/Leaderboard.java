package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leaderboard.Java
 * @author Sam R, Illia, Mart
 * @version 2
 * Last mod Date 29/11/2021
 */
public class Leaderboard {
    
    /** The Constant MAX_PLAYERS_IN_LEADERBOARD. */
    private final static int MAX_PLAYERS_IN_LEADERBOARD = 10;
	
    /** The level ID. */
    private int levelID;
    
    /** The ids to scores. */
    private HashMap<Integer, Integer> idsToScores;

    /**
     * creates the leaderboard.
     *
     * @param levelId id of the level
     * @param idsToScores puts ids to scores
     */
    public Leaderboard(int levelId, HashMap<Integer, Integer> idsToScores) {
        this.levelID = levelId;
        this.idsToScores = idsToScores;
    }

    /**
     * To string.
     *
     * @return leaderboard to a string
     */
    public String toString() {
        String str = "";
        for(Integer id : idsToScores.keySet()) {
            str += id +"," + idsToScores.get(id) + "\n";
        }
        return str;
    }

    /**
     * removes a player from the leaderboard.
     *
     * @param playerID player to be removed
     */
    public void removeFromLeaderBoard(int playerID) {
	this.idsToScores.remove(playerID);
    }

    /**
     * adds a player to the leaderboard.
     *
     * @param playerID player to be added
     * @param score score of the player
     */
    public void addToLeaderBoard(int playerID, int score) {
	if (this.idsToScores.keySet().size() == MAX_PLAYERS_IN_LEADERBOARD) {
	    if (idsToScores.containsKey(playerID)) {
		idsToScores.replace(playerID, idsToScores.get(playerID), score);
	    } else {
		List<Map.Entry<Integer, Integer>> keyVals = new ArrayList<>();
		keyVals.sort(Map.Entry.comparingByValue());
		for (Map.Entry<Integer, Integer> p : keyVals) {
		    if (p.getValue() < score) {
			removeFromLeaderBoard(p.getKey());
			this.idsToScores.put(playerID, score);
			break;
		    }
		}
	    }
	}
    }

    /**
     * adds a player to the leaderboard.
     *
     * @param player player to be added
     * @param score score of the player
     */
    public HashMap<Integer, Integer> getIdsToScores() {
        return idsToScores;
    }

    public void insertPlayer(Player player, int score) {
	addToLeaderBoard(player.getPlayerID(), score);
    }

    /**
     * removes a player from the leaderboard.
     *
     * @param player player to be removed
     */
    public void deletePlayer(Player player) {
	this.removeFromLeaderBoard(player.getPlayerID());
    }

}
