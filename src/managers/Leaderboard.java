package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {
    private final static int MAX_PLAYERS_IN_LEADERBOARD = 10;
    // needed?
    private int levelID;
    private HashMap<Integer, Integer> idsToScores;

    public Leaderboard(int levelId, HashMap<Integer, Integer> idsToScores) {
        // needed?
        this.levelID = levelId;
        this.idsToScores = idsToScores;
    }

    public String toString() {
        String str = "";
        for(Integer id : idsToScores.keySet()) {
            str += id + "," + idsToScores.get(id) + "\n";
        }
        return str;
    }
    public void removeFromLeaderBoard(int playerID) {
        this.idsToScores.remove(playerID);
    }

    public void addToLeaderBoard(int playerID, int score) {
        if(this.idsToScores.keySet().size() == MAX_PLAYERS_IN_LEADERBOARD) {
            if(idsToScores.containsKey(playerID)) {
                idsToScores.replace(playerID, idsToScores.get(playerID), score);
            } else {
                List<Map.Entry<Integer, Integer>> keyVals = new ArrayList<>();
                keyVals.sort(Map.Entry.comparingByValue());
                for(Map.Entry<Integer, Integer> p : keyVals) {
                    if(p.getValue() < score) {
                        removeFromLeaderBoard(p.getKey());
                        this.idsToScores.put(playerID, score);
                        break;
                    }
                }
            }
        }
    }

    public void insertPlayer(Player player, int score) {
        addToLeaderBoard(player.getPlayerID(), score);
    }

    public void deletePlayer(Player player) {
        this.removeFromLeaderBoard(player.getPlayerID());
    }

}
