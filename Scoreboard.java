import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class Scoreboard {
    private TreeMap<Integer, ArrayList<String>> winTree;
    private HashMap<String, Player> players;
    private int playedGames;

    public Scoreboard() {
        winTree = new TreeMap<>();
        players = new HashMap<>();
        playedGames = 0;
    }

    public void registerPlayer(String playerName) {
        if (!players.containsKey(playerName)) {
            Player newPlayer = new Player(playerName);
            players.put(playerName, newPlayer);

            if (!winTree.containsKey(0)) {
                winTree.put(0, new ArrayList<String>());
            }
            winTree.get(0).add(playerName);
        }
    }

    public boolean checkPlayer(String playerName) {
        return players.containsKey(playerName);
    }

    public void addGameResult(String winner, String loser, boolean draw) {
        playedGames++;

        if (!checkPlayer(winner)) {
            registerPlayer(winner);
        }

        if (!checkPlayer(loser)) {
            registerPlayer(loser);
        }

        Player pw = players.get(winner);
        Player pl = players.get(loser);

        if (draw) {
            pw.addDraw();
            pl.addDraw();
        } else {
            int oldWins = pw.getWins();
            ArrayList<String> list = winTree.get(oldWins);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(winner)) {
                    list.remove(i);
                    break;
                }
            }
            if (list.size() == 0) {
                winTree.remove(oldWins);
            }

            pw.addWin();
            pl.addLoss();

            int newWins = pw.getWins();
            if (!winTree.containsKey(newWins)) {
                winTree.put(newWins, new ArrayList<String>());
            }
            winTree.get(newWins).add(winner);
        }
    }

    public Player[] winRange(int lo, int hi) {
        ArrayList<Player> result = new ArrayList<Player>();

        ArrayList<Integer> keys = new ArrayList<Integer>();
        Iterator<Integer> it = winTree.keySet().iterator();
        while (it.hasNext()) {
            keys.add(it.next());
        }

        for (int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);
            if (key >= lo && key <= hi) {
                ArrayList<String> names = winTree.get(key);
                for (int j = 0; j < names.size(); j++) {
                    result.add(players.get(names.get(j)));
                }
            }
        }

        Player[] arr = new Player[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }

    public Player[] winSuccessor(int wins) {
        Integer successorKey = null;

        ArrayList<Integer> keys = new ArrayList<Integer>();
        Iterator<Integer> it = winTree.keySet().iterator();
        while (it.hasNext()) {
            keys.add(it.next());
        }

        for (int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);
            if (key > wins) {
                successorKey = key;
                break;
            }
        }

        if (successorKey == null) {
            return new Player[0];
        }

        ArrayList<String> names = winTree.get(successorKey);
        Player[] result = new Player[names.size()];
        for (int i = 0; i < names.size(); i++) {
            result[i] = players.get(names.get(i));
        }
        return result;
    }
}
