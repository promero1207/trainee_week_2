package com.calendario.rtg.scorekeeper;

public class Card {
    private String team;
    private String player;

    public Card(String team, String player) {
        this.team = team;
        this.player = player;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
