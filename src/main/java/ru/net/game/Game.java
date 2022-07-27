
package ru.net.game;

import java.util.*;

public class Game {
    private ArrayList<Player> playerList = new ArrayList<>();

    public void register(Player player) {
        playerList.add(player);
    }
    public ArrayList getAll() {
       return playerList;
    }

    public int findPlayerByName(String name) {
        for (int i = 0; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            if (player.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public int round(String playerName1, String playerName2) {
        int player1 = findPlayerByName(playerName1);
        int player2 = findPlayerByName(playerName2);
        if (player1 == -1) {
            throw new NotRegisteredException("Player 1 " + playerName1 + " is not registered");
        }
        if (player2 == -1) {
            throw new NotRegisteredException("Player 2 " + playerName2 + " is not registered");
        }
        int strength1 = playerList.get(player1).getStrength();
        int strength2 = playerList.get(player2).getStrength();
        if (strength1 > strength2) {
            return 1;
        }
        if (strength1 == strength2) {
            return 0;
        }
        return 2;
    }
}
