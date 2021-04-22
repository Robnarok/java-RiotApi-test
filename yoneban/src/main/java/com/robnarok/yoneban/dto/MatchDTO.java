package com.robnarok.yoneban.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MatchDTO {

    List<BanDTO> bans;

    public MatchDTO(){
        bans = new ArrayList<BanDTO>();
    }

    public void add(BanDTO banDTO){
        bans.add(banDTO);
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "bans=" + bans +
                '}';
    }
}
