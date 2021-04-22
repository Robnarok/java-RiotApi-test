package com.robnarok.yoneban.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BanDTO {
    private String championId;
    private String pickTurn;

    public BanDTO(String championId, String pickTurn){
        this.championId = championId;
        this.pickTurn = pickTurn;
    }

    public BanDTO() {

    }

    public String getChampionId() {
        return championId;
    }

    public String getPickTurn() {
        return pickTurn;
    }

    public void setChampionId(String championId){
        this.championId = championId;
    }

    public void setPickTurn(String pickTurn) {
        this.pickTurn = pickTurn;
    }

    @Override
    public String toString() {
        return "BanDTO{" +
                "championId='" + championId + '\'' +
                ", pickTurn='" + pickTurn + '\'' +
                '}';
    }
}
