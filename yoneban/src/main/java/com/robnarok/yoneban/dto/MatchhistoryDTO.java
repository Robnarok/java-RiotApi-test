package com.robnarok.yoneban.dto;

import java.util.List;

public class MatchhistoryDTO {

    public List<String> getMatches() {
        return matches;
    }

    List<String> matches;

    public MatchhistoryDTO(List<String> matches) {
        this.matches = matches;
    }
}
