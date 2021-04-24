package com.robnarok.yoneban.wrapper;

import java.util.ArrayList;
import java.util.List;

public class Matchdata {

    List<Ban> bans;
    List<Player> players;
    String date="0";
    String gameMode="NaN";

    public Matchdata(){
        bans = new ArrayList<Ban>();
        players = new ArrayList<Player>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void addBan(Ban banDTO){
        bans.add(banDTO);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public String printPlayer(){
        String ret ="";
        for (Player player : players) {
            ret += player.toString();
            ret += "\n";
        }
        return ret;
    }

    public boolean didChampGetBanned(String championId){
        for (Ban ban : bans) {
            if (ban.isChampion(championId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "bans=" + bans +
                '}';
    }
}