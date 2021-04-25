package com.robnarok.yoneban.model;

import com.robnarok.yoneban.wrapper.Matchdata;

import javax.persistence.*;

@Entity
public class PersistentMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String matchID;
    boolean gotBanned;
    int counter;

    //Not Persisten, and really makes the Code 100 Worse.. but okay
    @Transient
    Matchdata matchdata;

    public Matchdata getMatchdata() {
        return matchdata;
    }

    public PersistentMatch() {
    }

    public PersistentMatch(String matchID, boolean gotBanned, int counter) {
        this.matchID = matchID;
        this.gotBanned = gotBanned;
        this.counter = counter;
    }

    public PersistentMatch(Matchdata matchdata, String championId, int counter){
        this.matchID = matchdata.getMatchId();
        this.gotBanned = matchdata.didChampGetBanned("777");
        this.counter = counter;
        this.matchdata = matchdata;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public boolean isGotBanned() {
        return gotBanned;
    }

    public void setGotBanned(boolean gotBanned) {
        this.gotBanned = gotBanned;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
