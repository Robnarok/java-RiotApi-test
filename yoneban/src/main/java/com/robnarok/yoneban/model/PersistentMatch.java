package com.robnarok.yoneban.model;

import com.robnarok.yoneban.wrapper.Matchdata;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PersistentMatch {

    @Id
    @GeneratedValue
    Long id;

    String matchID;
    boolean gotBanned;
    String championID;
    String championName;

    @Transient
    int counter;

    //Not Persistent, and really makes the Code 100 Worse.. but okay
    @Transient
    Matchdata matchdata;

    public PersistentMatch(Matchdata matchdata, String championId, String championName, int counter) {
        this.matchID = matchdata.getMatchId();
        this.gotBanned = matchdata.didChampGetBanned(championId);
        this.championID = championId;
        this.championName = championName;

        this.counter = counter;
        this.matchdata = matchdata;
    }

}
