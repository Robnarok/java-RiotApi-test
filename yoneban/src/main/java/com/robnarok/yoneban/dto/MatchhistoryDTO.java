package com.robnarok.yoneban.dto;

import java.util.List;

public class MatchhistoryDTO {


    List<String> matches;

    public MatchhistoryDTO(List<String> matches) {
        this.matches = matches;
    }

    public List<String> getMatches() {
        return matches;
    }

    public void setMatches(List<String> matches) {
        this.matches = matches;
    }
}
