package controller.service;

import Poker.Player;

import java.util.HashMap;
import java.util.Map;

public class TableManager {
    private static final Map<String, RoundService> tables = new HashMap<>();
    private static TableManager instance;

    private TableManager() {
        // private constructor to prevent instantiation
    }

    public static TableManager getInstance() {
        if (instance == null) {
            synchronized (TableManager.class) {
                if (instance == null) {
                    instance = new TableManager();
                }
            }
        }
        return instance;
    }

    public RoundService getTable(String tableId) {
        return tables.get(tableId);
    }

    public void createTable(String tableId) {
        RoundService roundService = new RoundService();
        tables.put(tableId, roundService);
    }

    public void addPlayerToTable(String tableId, Player player) {
        RoundService roundService = tables.get(tableId);
        roundService.addPlayer(player);
    }
}