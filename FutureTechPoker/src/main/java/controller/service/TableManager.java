package controller.service;

import Poker.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static Map<String, RoundService> getTables(){
        return tables;
    }

    public static String sortGames(String sortBy) {
        Map<String, RoundService> tables = TableManager.getTables();
        StringBuilder htmlBuilder = new StringBuilder();
        String label = "label";

        switch (sortBy){
            case "players": label = "Players"; break;
            case "blind": label = "Big Blind"; break;
            case "start_bet": label = "Start Bet"; break;
        }

        final String finalLabel = label;

        if (tables != null) {
            htmlBuilder.append("<div class=\"button-container table-buttons\">");
            tables.entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(returnSortOptionValue(e2, sortBy), returnSortOptionValue(e1, sortBy)))
                    .forEach(entry -> {
                        htmlBuilder.append("<button onclick=\"location.href='m_table.jsp?n=").append(entry.getKey()).append("';\"> ").append(finalLabel).append(": ").append(returnSortOptionValue(entry, sortBy)).append("</button>");
                    });
            htmlBuilder.append("</div>");
        } else {
            return "<p>No Active Tables to Sort</p>";

        }

        return htmlBuilder.toString();
    }

    public static int returnSortOptionValue(Map.Entry<String, RoundService> entry, String sortOption){
        switch (sortOption) {
            case "blind": return (int) entry.getValue().round.getBigBlind();
            case "start_bet": return (int) entry.getValue().round.getStarting_bet();
            default: return entry.getValue().round.getPlayers().size();
        }
    }

    public static String getMostFilledTable() {
        Map<String, RoundService> tables = TableManager.getTables();
        String mostFilledTableLink = "window.location.href='home.jsp'"; // Default link
        int maxPlayers = 0;

        for (Map.Entry<String, RoundService> entry : tables.entrySet()) {
            RoundService roundService = entry.getValue();
            int playerCount = roundService.round.getPlayers().size();

            if (playerCount > maxPlayers) {
                maxPlayers = playerCount;
                mostFilledTableLink = "window.location.href='m_table.jsp?n=" + entry.getKey() + "'";
            }
        }
        return mostFilledTableLink;
    }

}