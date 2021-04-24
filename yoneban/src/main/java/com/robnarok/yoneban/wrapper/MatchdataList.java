package com.robnarok.yoneban.wrapper;

import java.util.ArrayList;
import java.util.List;

public class MatchdataList {
    List<Matchdata> matchdata;

    public MatchdataList() {
        this.matchdata = new ArrayList<Matchdata>();
    }

    public void addMatchdata(Matchdata matchdata) {
        this.matchdata.add(matchdata);
    }

    public void filterValidMatches(){
        for (Matchdata matchdata : matchdata){

        }
    }
}
