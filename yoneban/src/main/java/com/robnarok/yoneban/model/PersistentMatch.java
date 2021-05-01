package com.robnarok.yoneban.model;

import com.robnarok.yoneban.wrapper.Matchdata;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PersistentMatch {

    @Id
    @GeneratedValue
    Long id;

    String matchID;
    boolean gotBanned;
    String championID;
    String championName;
    int counter;

    //Not Persistent, and really makes the Code 100 Worse.. but okay
    @Transient
    Matchdata matchdata;


    public Matchdata getMatchdata() {
        return matchdata;
    }

    public PersistentMatch(Matchdata matchdata, String championId, String championName, int counter) {
        this.matchID = matchdata.getMatchId();
        this.gotBanned = matchdata.didChampGetBanned(championId);
        this.championID = championId;
        this.championName = championName;
        this.counter = counter;

        this.matchdata = matchdata;

    }

    public PersistentMatch(){

    }

    // Boilerplate Code
//    public String getMatchID() {
//        return matchID;
//    }
//
//    public void setMatchID(String matchID) {
//        this.matchID = matchID;
//    }
//
//    public boolean isGotBanned() {
//        return gotBanned;
//    }
//
//    public void setGotBanned(boolean gotBanned) {
//        this.gotBanned = gotBanned;
//    }
//
//    public int getCounter() {
//        return counter;
//    }
//
//    public void setCounter(int counter) {
//        this.counter = counter;
//    }
}
